package com.shoshin.data.remote.entities.responses

import com.shoshin.data.remote.entities.CharacterRemote

data class CharactersResponse(
    val info: CharactersResponseInfo,
    val results: List<CharacterRemote>
)