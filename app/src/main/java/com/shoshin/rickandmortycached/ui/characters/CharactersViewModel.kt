package com.shoshin.rickandmortycached.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.shoshin.domain.entities.CharacterDomain
import com.shoshin.domain.repositories.ICharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    charactersRepository: ICharactersRepository
): ViewModel() {

    @ExperimentalPagingApi
    val charactersFlow: Flow<PagingData<CharacterDomain>> =
        charactersRepository.getCharacters().cachedIn(viewModelScope).map { it ->
            it.map {
                Log.e("ch","ch=$it")
                it
            }
        }
}