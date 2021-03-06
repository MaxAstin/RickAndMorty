package com.bunbeauty.rickandmorty.data

import com.bunbeauty.rickandmorty.data.mapper.CharacterMapper
import com.bunbeauty.rickandmorty.domain.Character
import com.bunbeauty.rickandmorty.domain.ICharacterRepository
import javax.inject.Inject
import com.bunbeauty.rickandmorty.domain.Result

class CharacterRepository @Inject constructor(
    private val characterApiService: CharacterApiService,
    private val characterMapper: CharacterMapper
) : BaseRepository(), ICharacterRepository {

    override suspend fun loadCharacterList(pageNumber: Int): Result<List<Character>> {
        return characterApiService.getCharacters(pageNumber).handleResponse { resultDTO ->
            resultDTO.results.map(characterMapper::toCharacter)
        }
    }
}