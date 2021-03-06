package com.bunbeauty.rickandmorty

import com.bunbeauty.rickandmorty.Constants.ERROR_CODE
import com.bunbeauty.rickandmorty.Constants.ERROR_MESSAGE
import com.bunbeauty.rickandmorty.Constants.PAGE_NUMBER
import com.bunbeauty.rickandmorty.Constants.SUCCESS_CODE
import com.bunbeauty.rickandmorty.Constants.TEST_NAME
import com.bunbeauty.rickandmorty.Constants.TEST_PHOTO_LINK
import com.bunbeauty.rickandmorty.data.CharacterApiService
import com.bunbeauty.rickandmorty.data.CharacterRepository
import com.bunbeauty.rickandmorty.data.Constants.ERROR_MESSAGE_BODY_IS_NULL
import com.bunbeauty.rickandmorty.data.mapper.CharacterMapper
import com.bunbeauty.rickandmorty.domain.Character
import com.bunbeauty.rickandmorty.domain.Result
import com.bunbeauty.rickandmorty.utils.CharacterApiTestUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharacterRepositoryTest {

    private lateinit var characterApiService: CharacterApiService
    private lateinit var characterMapper: CharacterMapper
    private lateinit var characterRepository: CharacterRepository

    private val characterApiTestUtil = CharacterApiTestUtil()

    @Before
    fun setup() {
        characterApiService = Mockito.mock(CharacterApiService::class.java)
        characterMapper = CharacterMapper()

        characterRepository = CharacterRepository(characterApiService, characterMapper)
    }

    @Test
    fun whenResponseIsSuccessful() = runBlocking {
        val expectedResult = Result.Success(
            listOf(
                Character(
                    name = TEST_NAME,
                    photoLink = TEST_PHOTO_LINK,
                )
            )
        )
        Mockito.`when`(characterApiService.getCharacters(PAGE_NUMBER))
            .thenReturn(characterApiTestUtil.getSuccessResponse())

        val result = characterRepository.loadCharacterList(PAGE_NUMBER)

        assertEquals(expectedResult, result)
    }

    @Test
    fun whenResponseIsSuccessfulAndEmpty() = runBlocking {
        val expectedResult = Result.Error<Character>("$SUCCESS_CODE $ERROR_MESSAGE_BODY_IS_NULL")
        Mockito.`when`(characterApiService.getCharacters(PAGE_NUMBER))
            .thenReturn(characterApiTestUtil.getEmptyResponse())

        val result = characterRepository.loadCharacterList(PAGE_NUMBER)

        assertEquals(expectedResult, result)
    }

    @Test
    fun whenErrorResponse() = runBlocking {
        val expectedResult = Result.Error<Character>("$ERROR_CODE $ERROR_MESSAGE")
        Mockito.`when`(characterApiService.getCharacters(PAGE_NUMBER))
            .thenReturn(characterApiTestUtil.getResponseWithError())

        val result = characterRepository.loadCharacterList(PAGE_NUMBER)

        assertEquals(expectedResult, result)
    }
}