package com.bunbeauty.rickandmorty.domain

interface ICharacterInteractor {
    suspend fun loadCharacterFirstPage(): Result<List<Character>>
}