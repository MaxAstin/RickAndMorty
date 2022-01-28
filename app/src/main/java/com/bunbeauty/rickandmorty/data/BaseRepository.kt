package com.bunbeauty.rickandmorty.data

import com.bunbeauty.rickandmorty.data.Constants.BODY_IS_NULL
import retrofit2.Response
import com.bunbeauty.rickandmorty.domain.Result

abstract class BaseRepository {

    inline fun <DTO, T> Response<DTO>.handleResponse(map: (DTO) -> T): Result<T> {
        val responseBody = body()
        return if (isSuccessful) {
            if (responseBody == null) {
                Result.Error("${code()} $BODY_IS_NULL")
            } else {
                Result.Success(map(responseBody))
            }
        } else {
            Result.Error("${code()} ${message()}")
        }
    }
}