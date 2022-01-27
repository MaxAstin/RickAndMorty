package com.bunbeauty.rickandmorty.domain

interface ICharacterRepository {

    suspend fun loadCharacterList(): Result<List<Character>>
}