package com.bunbeauty.rickandmorty.domain

interface ICharacterRepository {

    suspend fun loadCharacterList(pageNumber: Int): Result<List<Character>>
}