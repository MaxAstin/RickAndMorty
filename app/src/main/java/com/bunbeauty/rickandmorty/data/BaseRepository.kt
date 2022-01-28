package com.bunbeauty.rickandmorty.data

import com.bunbeauty.rickandmorty.data.Constants.ERROR_MESSAGE_BODY_IS_NULL
import retrofit2.Response
import com.bunbeauty.rickandmorty.domain.Result

abstract class BaseRepository {

    protected inline fun <DTO, T> Response<DTO>.handleResponse(map: (DTO) -> T): Result<T> {
        val responseBody = body()
        return if (isSuccessful) {
            if (responseBody == null) {
                Result.Error("${code()} $ERROR_MESSAGE_BODY_IS_NULL")
            } else {
                Result.Success(map(responseBody))
            }
        } else {
            Result.Error("${code()} ${message()}")
        }
    }
}