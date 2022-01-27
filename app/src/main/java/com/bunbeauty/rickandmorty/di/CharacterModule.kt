package com.bunbeauty.rickandmorty.di

import com.bunbeauty.rickandmorty.data.CharacterRepository
import com.bunbeauty.rickandmorty.domain.CharacterInteractor
import com.bunbeauty.rickandmorty.domain.ICharacterInteractor
import com.bunbeauty.rickandmorty.domain.ICharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CharacterModule {

    @Binds
    fun bindsCharacterInteractor(characterInteractor: CharacterInteractor): ICharacterInteractor

    @Binds
    fun bindsCharacterRepository(characterRepository: CharacterRepository): ICharacterRepository
}