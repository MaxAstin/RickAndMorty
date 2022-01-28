package com.bunbeauty.rickandmorty.domain

import javax.inject.Inject

class CharacterInteractor @Inject constructor(private val characterRepository: ICharacterRepository) :
    ICharacterInteractor {

    override suspend fun loadCharacterFirstPage(): Result<List<Character>> {
        return characterRepository.loadCharacterList(1)
    }

}