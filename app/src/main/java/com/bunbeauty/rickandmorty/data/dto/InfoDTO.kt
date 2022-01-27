package com.bunbeauty.rickandmorty.data.dto

data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)