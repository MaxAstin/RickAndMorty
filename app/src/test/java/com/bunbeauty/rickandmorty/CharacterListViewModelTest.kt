package com.bunbeauty.rickandmorty

import com.bunbeauty.rickandmorty.Constants.ERROR_MESSAGE
import com.bunbeauty.rickandmorty.Constants.TEST_NAME
import com.bunbeauty.rickandmorty.Constants.TEST_PHOTO_LINK
import com.bunbeauty.rickandmorty.domain.Character
import com.bunbeauty.rickandmorty.domain.ICharacterInteractor
import com.bunbeauty.rickandmorty.domain.Result
import com.bunbeauty.rickandmorty.presentation.state.State
import com.bunbeauty.rickandmorty.presentation.view_model.CharacterListViewModel
import com.bunbeauty.rickandmorty.ui.item.CharacterItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterListViewModelTest {

    private lateinit var characterInteractor: ICharacterInteractor
    private lateinit var characterListViewModel: CharacterListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        characterInteractor = Mockito.mock(ICharacterInteractor::class.java)
    }

    @Test
    fun whenResultIsSuccessful() = runBlocking {
        Mockito.`when`(characterInteractor.loadCharacterList())
            .thenReturn(getSuccessResult())
        characterListViewModel = CharacterListViewModel(characterInteractor)

        val expectedState = State.Success(
            listOf(
                CharacterItem(
                    name = TEST_NAME,
                    photoLink = TEST_PHOTO_LINK
                )
            )
        )

        assertEquals(expectedState, characterListViewModel.characterItemListState.value)
    }

    @Test
    fun whenErrorResult() = runBlocking {
        Mockito.`when`(characterInteractor.loadCharacterList())
            .thenReturn(getErrorResult())
        characterListViewModel = CharacterListViewModel(characterInteractor)

        val expectedState = State.Error<List<Character>>(
            message = ERROR_MESSAGE
        )

        assertEquals(expectedState, characterListViewModel.characterItemListState.value)
    }

    fun getSuccessResult(): Result.Success<List<Character>> {
        return Result.Success(
            listOf(
                Character(
                    name = TEST_NAME,
                    photoLink = TEST_PHOTO_LINK,
                )
            )
        )
    }

    fun getErrorResult(): Result.Error<List<Character>> {
        return Result.Error(
            message = ERROR_MESSAGE
        )
    }
}