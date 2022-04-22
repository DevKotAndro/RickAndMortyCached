package com.shoshin.data.db.main

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shoshin.data.db.entities.CharacterDbo

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterDbo>)

    @Query("SELECT * FROM characters")
    fun getAll(): PagingSource<Int, CharacterDbo>

    @Query("DELETE FROM characters")
    suspend fun deleteAll()
}