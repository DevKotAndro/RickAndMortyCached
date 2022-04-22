package com.shoshin.domain.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.shoshin.domain.common.Reaction
import com.shoshin.domain.entities.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface ICharactersRepository {
    @ExperimentalPagingApi
    fun getCharacters(): Flow<PagingData<CharacterDomain>>
    fun getCharacter(id: Int): Flow<Reaction<CharacterDomain>>
}
