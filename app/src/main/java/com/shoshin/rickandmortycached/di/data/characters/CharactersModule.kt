package com.shoshin.rickandmortycached.di.data.characters

import com.shoshin.data.db.entities.CharacterDbo
import com.shoshin.data.mappers.CharacterDboToDomainMapper
import com.shoshin.data.mappers.CharacterRemoteToDboMapper
import com.shoshin.data.mappers.CharacterRemoteToDomainMapper
import com.shoshin.data.remote.entities.CharacterRemote
import com.shoshin.domain.common.Mapper
import com.shoshin.domain.entities.CharacterDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CharactersModule {
    @Provides
    fun provideDbToDomainMapper(): Mapper<CharacterDbo, CharacterDomain> =
        CharacterDboToDomainMapper()

    @Provides
    fun provideRemoteToDomainMapper(): Mapper<CharacterRemote, CharacterDomain> =
        CharacterRemoteToDomainMapper()

    @Provides
    fun provideRemoteToDbMapper(): Mapper<CharacterRemote, CharacterDbo> =
        CharacterRemoteToDboMapper()
}