package com.shoshin.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shoshin.data.db.entities.CharacterDbo
import com.shoshin.data.db.entities.RemoteKeyDbo
import com.shoshin.data.db.main.AppDatabase
import com.shoshin.data.remote.ICharactersService
import com.shoshin.data.remote.entities.CharacterRemote
import com.shoshin.domain.common.Mapper
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val api: ICharactersService,
    private val db: AppDatabase,
    private val mapperRemoteToDbo: Mapper<CharacterRemote, CharacterDbo>
): RemoteMediator<Int, CharacterDbo>() {

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterDbo>
    ): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {
            Log.e("page", "page=$page")
            val response = api.getCharacters(page)
            val items = response.results
            val isEndOfList = response.info.next == null
            db.withTransaction {
                if(loadType == LoadType.REFRESH) {
//                    Log.e("refresh page", "refresh.page=${page}")
                    db.charactersDao().deleteAll()
                    db.remoteKeysDao().deleteAll()
                }
                val prevKey = if(page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = items.map {
                    RemoteKeyDbo(it.id, prevKey = prevKey, nextKey = nextKey)
                }
                val dboItems = mapperRemoteToDbo.map(items)
                db.charactersDao().insertAll(dboItems)
                db.remoteKeysDao().insertAll(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, CharacterDbo>): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRefreshKey(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
        }
    }

    private suspend fun getRefreshKey(state: PagingState<Int, CharacterDbo>): RemoteKeyDbo? {
        val anchorPosition = state.anchorPosition ?: return null
        val character = state.closestItemToPosition(anchorPosition) ?: return null
        return db.remoteKeysDao().remoteKey(character.id)
    }

    //    return state.anchorPosition?.let { position ->
    //        state.closestItemToPosition(position)?.id?.let { repoId ->
    //   repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
    //        }
    //    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, CharacterDbo>): RemoteKeyDbo? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { character -> db.remoteKeysDao().remoteKey(character.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, CharacterDbo>): RemoteKeyDbo? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { character -> db.remoteKeysDao().remoteKey(character.id) }
    }
}
