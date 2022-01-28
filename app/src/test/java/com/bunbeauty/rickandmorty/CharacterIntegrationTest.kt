package com.bunbeauty.rickandmorty

import com.bunbeauty.rickandmorty.Constants.ERROR_MESSAGE
import com.bunbeauty.rickandmorty.Constants.TEST_NAME
import com.bunbeauty.rickandmorty.Constants.TEST_PHOTO_LINK
import com.bunbeauty.rickandmorty.data.CharacterApiService
import com.bunbeauty.rickandmorty.data.CharacterRepository
import com.bunbeauty.rickandmorty.data.Constants.BODY_IS_NULL
import com.bunbeauty.rickandmorty.data.mapper.CharacterMapper
import com.bunbeauty.rickandmorty.domain.Character
import com.bunbeauty.rickandmorty.domain.CharacterInteractor
import com.bunbeauty.rickandmorty.presentation.state.State
import com.bunbeauty.rickandmorty.presentation.view_model.CharacterListViewModel
import com.bunbeauty.rickandmorty.ui.item.CharacterItem
import com.bunbeauty.rickandmorty.utils.CharacterApiTestUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterIntegrationTest {

    private lateinit var characterApiService: CharacterApiService
    private lateinit var characterMapper: CharacterMapper
    private lateinit var characterRepository: CharacterRepository
    private lateinit var characterInteractor: CharacterInteractor
    private lateinit var characterListViewModel: CharacterListViewModel

    private val characterApiTestUtil = CharacterApiTestUtil()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        characterApiService = Mockito.mock(CharacterApiService::class.java)
        characterMapper = CharacterMapper()

        characterRepository = CharacterRepository(characterApiService, characterMapper)
        characterInteractor = CharacterInteractor(characterRepository)
    }

    @Test
    fun whenResponseIsSuccessful() = runBlocking {
        val expectedState = State.Success(
            listOf(
                CharacterItem(
                    name = TEST_NAME,
                    photoLink = TEST_PHOTO_LINK
                )
            )
        )

        Mockito.`when`(characterApiService.getCharacters(PAGE_NUMBER))
            .thenReturn(characterApiTestUtil.getSuccessResponse())
        characterListViewModel = CharacterListViewModel(characterInteractor)

        assertEquals(expectedState, characterListViewModel.characterItemListState.value)
    }

    @Test
    fun whenResponseIsSuccessfulAndEmpty() = runBlocking {
        val expectedState = State.Error<List<Character>>(
            message = "$SUCCESS_CODE $BODY_IS_NULL"
        )

        Mockito.`when`(characterApiService.getCharacters(PAGE_NUMBER))
            .thenReturn(characterApiTestUtil.getEmptyResponse())
        characterListViewModel = CharacterListViewModel(characterInteractor)

        assertEquals(expectedState, characterListViewModel.characterItemListState.value)
    }

    @Test
    fun whenErrorResponse() = runBlocking {
        val expectedState = State.Error<List<Character>>(
            message = "$ERROR_CODE $ERROR_MESSAGE"
        )

        Mockito.`when`(characterApiService.getCharacters(PAGE_NUMBER))
            .thenReturn(characterApiTestUtil.getResponseWithError())
        characterListViewModel = CharacterListViewModel(characterInteractor)

        assertEquals(expectedState, characterListViewModel.characterItemListState.value)
    }

    companion object {
        private const val PAGE_NUMBER = 1
        private const val SUCCESS_CODE = 200
        private const val ERROR_CODE = 404
    }
}