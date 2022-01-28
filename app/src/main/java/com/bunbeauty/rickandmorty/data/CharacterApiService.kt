package com.bunbeauty.rickandmorty.data

import com.bunbeauty.rickandmorty.data.dto.CharacterDTO
import com.bunbeauty.rickandmorty.data.dto.ResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): Response<ResultDTO<CharacterDTO>>
}