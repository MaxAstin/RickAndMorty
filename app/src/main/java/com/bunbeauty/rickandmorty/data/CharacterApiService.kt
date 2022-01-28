package com.bunbeauty.rickandmorty.data

import com.bunbeauty.rickandmorty.data.Constants.API_CHARACTER_PATH
import com.bunbeauty.rickandmorty.data.Constants.API_QUERY_PARAMETER_PAGE
import com.bunbeauty.rickandmorty.data.dto.CharacterDTO
import com.bunbeauty.rickandmorty.data.dto.ResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET(API_CHARACTER_PATH)
    suspend fun getCharacters(@Query(API_QUERY_PARAMETER_PAGE) page: Int): Response<ResultDTO<CharacterDTO>>
}