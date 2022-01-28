package com.bunbeauty.rickandmorty.utils

import com.bunbeauty.rickandmorty.Constants.ERROR_CODE
import com.bunbeauty.rickandmorty.Constants.ERROR_MESSAGE
import com.bunbeauty.rickandmorty.Constants.SUCCESS_CODE
import com.bunbeauty.rickandmorty.Constants.TEST_NAME
import com.bunbeauty.rickandmorty.Constants.TEST_PHOTO_LINK
import com.bunbeauty.rickandmorty.data.dto.CharacterDTO
import com.bunbeauty.rickandmorty.data.dto.InfoDTO
import com.bunbeauty.rickandmorty.data.dto.ResultDTO
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Response

class CharacterApiTestUtil {

    fun getSuccessResponse(): Response<ResultDTO<CharacterDTO>> {
        return Response.success(
            SUCCESS_CODE,
            ResultDTO(
                info = InfoDTO(
                    count = 0,
                    pages = 0,
                    next = "",
                    prev = ""
                ),
                results = listOf(
                    CharacterDTO(
                        id = 1,
                        name = TEST_NAME,
                        image = TEST_PHOTO_LINK
                    )
                )
            )
        )
    }

    fun getEmptyResponse(): Response<ResultDTO<CharacterDTO>> {
        return Response.success<ResultDTO<CharacterDTO>>(SUCCESS_CODE, null)
    }

    fun getResponseWithError(): Response<ResultDTO<CharacterDTO>> {
        return Response.error(
            ResponseBody.create(null, ""),
            okhttp3.Response.Builder()
                .code(ERROR_CODE)
                .message(ERROR_MESSAGE)
                .protocol(Protocol.HTTP_1_0)
                .request(
                    Request.Builder()
                        .url("https://host")
                        .build()
                )
                .build()
        )
    }
}