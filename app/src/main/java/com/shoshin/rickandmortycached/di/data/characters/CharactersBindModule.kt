package com.shoshin.rickandmortycached.di.data.characters

import com.shoshin.data.repositories.CharactersRepository
import com.shoshin.domain.repositories.ICharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CharactersBindModule {
    @Binds
    fun bindCharactersRepository(repo: CharactersRepository): ICharactersRepository
}