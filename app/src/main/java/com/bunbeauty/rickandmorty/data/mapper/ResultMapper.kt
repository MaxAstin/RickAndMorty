package com.bunbeauty.rickandmorty.data.mapper

import com.bunbeauty.rickandmorty.data.dto.ResultDTO
import javax.inject.Inject

class ResultMapper @Inject constructor() {

    fun <T> toList(resultDTO: ResultDTO<T>): List<T> {
        return resultDTO.results
    }
}