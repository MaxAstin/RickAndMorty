package com.bunbeauty.rickandmorty.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.bunbeauty.rickandmorty.domain.Character
import com.bunbeauty.rickandmorty.domain.ICharacterInteractor
import com.bunbeauty.rickandmorty.presentation.state.State
import com.bunbeauty.rickandmorty.ui.item.CharacterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterInteractor: ICharacterInteractor
) : BaseViewModel() {

    private val mutableCharacterItemListState: MutableStateFlow<State<List<CharacterItem>>> =
        MutableStateFlow(State.Loading())
    val characterItemListState: StateFlow<State<List<CharacterItem>>> =
        mutableCharacterItemListState.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            mutableCharacterItemListState.value =
                characterInteractor.loadCharacterFirstPage().toState { characterList ->
                    characterList.map(::toCharacterItem)
                }
        }
    }

    private fun toCharacterItem(character: Character): CharacterItem {
        return CharacterItem(
            name = character.name,
            photoLink = character.photoLink
        )
    }

}