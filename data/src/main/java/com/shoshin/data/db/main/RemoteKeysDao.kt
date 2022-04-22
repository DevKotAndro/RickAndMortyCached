package com.shoshin.data.db.main

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoshin.data.db.entities.RemoteKeyDbo

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<RemoteKeyDbo>)

    @Query("SELECT * FROM remote_keys WHERE characterId = :characterId")
    suspend fun remoteKey(characterId: Int): RemoteKeyDbo?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()
}
