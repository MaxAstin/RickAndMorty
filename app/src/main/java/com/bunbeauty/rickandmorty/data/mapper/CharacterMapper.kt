package com.bunbeauty.rickandmorty.data.mapper

import com.bunbeauty.rickandmorty.data.dto.CharacterDTO
import com.bunbeauty.rickandmorty.domain.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor() {

    fun toCharacter(characterDTO: CharacterDTO): Character {
        return Character(
            name = characterDTO.name,
            photoLink = characterDTO.image
        )
    }
}