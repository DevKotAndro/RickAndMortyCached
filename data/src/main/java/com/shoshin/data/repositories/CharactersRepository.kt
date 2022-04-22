package com.shoshin.data.repositories

import android.util.Log
import androidx.paging.*
import com.shoshin.data.CharactersRemoteMediator
import com.shoshin.data.db.entities.CharacterDbo
import com.shoshin.data.db.main.AppDatabase
import com.shoshin.data.remote.BaseRemoteSource
import com.shoshin.data.remote.ICharactersService
import com.shoshin.data.remote.entities.CharacterRemote
import com.shoshin.domain.common.Mapper
import com.shoshin.domain.common.Reaction
import com.shoshin.domain.entities.CharacterDomain
import com.shoshin.domain.repositories.ICharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val db: AppDatabase,
    private val api: ICharactersService,
    private val mapperRemoteToDbMapper: Mapper<CharacterRemote, CharacterDbo>,
    private val mapperDbToDomain: Mapper<CharacterDbo, CharacterDomain>,
    private val mapperRemoteToDomain: Mapper<CharacterRemote, CharacterDomain>
): ICharactersRepository, BaseRemoteSource() {
    companion object {
        const val PAGE_SIZE = 20
        const val MAX_SIZE = 1000
    }

    @ExperimentalPagingApi
    override fun getCharacters(): Flow<PagingData<CharacterDomain>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = CharactersRemoteMediator(
                api,
                db,
                mapperRemoteToDbMapper
            ),
            pagingSourceFactory = { db.charactersDao().getAll() }
        ).flow.map { pagingData ->
//            Log.e("pagingData", "pagingData=$pagingData")


//            Log.e("characters", "characters=${pagingData?.}")
            pagingData.map { character ->
//                Log.e("character", "character=$character")
                mapperDbToDomain.map(character)
            }
        }


    override fun getCharacter(id: Int): Flow<Reaction<CharacterDomain>> = flow {
        emit(Reaction.Progress())
        val characterRemoteReaction = safeApiCall { api.getCharacter(id) }
        val reaction = characterRemoteReaction.map(mapperRemoteToDomain::map)
        emit(reaction)
    }
}