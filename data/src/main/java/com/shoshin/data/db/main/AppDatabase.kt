package com.shoshin.data.db.main

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shoshin.data.db.converters.ListConverter
import com.shoshin.data.db.entities.CharacterDbo
import com.shoshin.data.db.entities.RemoteKeyDbo

@Database(version = 1, entities = [CharacterDbo::class, RemoteKeyDbo::class])
@TypeConverters(ListConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}