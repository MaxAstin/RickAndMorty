package com.bunbeauty.rickandmorty.view_model

import androidx.lifecycle.viewModelScope
import com.bunbeauty.rickandmorty.domain.Character
import com.bunbeauty.rickandmorty.domain.ICharacterInteractor
import com.bunbeauty.rickandmorty.ui.item.CharacterItem
import com.bunbeauty.rickandmorty.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterInteractor: ICharacterInteractor
) : BaseViewModel() {

    private val mutableCharacterItemListState: MutableStateFlow<State<List<CharacterItem>>> =
        MutableStateFlow(State.Loading())
    val characterItemListState: StateFlow<State<List<CharacterItem>>> =
        mutableCharacterItemListState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            mutableCharacterItemListState.value =
                characterInteractor.loadCharacterList().toState { characterList ->
                    characterList.map(::toCharacterItem)
                }
        }
    }

    fun toCharacterItem(character: Character): CharacterItem {
        return CharacterItem(
            name = character.name,
            photoLink = character.photoLink
        )
    }

}