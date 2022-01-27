package com.bunbeauty.rickandmorty.data.dto

data class ResultDTO<T>(
    val info: InfoDTO,
    val results: List<T>
)
