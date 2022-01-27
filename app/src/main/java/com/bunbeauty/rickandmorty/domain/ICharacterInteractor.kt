package com.bunbeauty.rickandmorty.domain

interface ICharacterInteractor {
    suspend fun loadCharacterList(): Result<List<Character>>
}