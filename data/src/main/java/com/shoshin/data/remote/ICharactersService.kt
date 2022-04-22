package com.shoshin.data.remote

import com.shoshin.data.remote.entities.CharacterRemote
import com.shoshin.data.remote.entities.responses.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ICharactersService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path(value = "id", encoded = true) id: Int
    ): CharacterRemote
}