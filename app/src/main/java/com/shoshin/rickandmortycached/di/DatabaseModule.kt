package com.shoshin.rickandmortycached.di

import android.content.Context
import androidx.room.Room
import com.shoshin.data.db.main.AppDatabase
import com.shoshin.data.db.main.CharactersDao
import com.shoshin.data.db.main.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(db: AppDatabase): CharactersDao = db.charactersDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(db: AppDatabase): RemoteKeysDao = db.remoteKeysDao()
}