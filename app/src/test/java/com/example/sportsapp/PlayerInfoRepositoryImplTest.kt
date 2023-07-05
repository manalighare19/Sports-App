package com.example.sportsapp

import com.example.sportsapp.data.Player
import com.example.sportsapp.data.PlayersAPIResponse
import com.example.sportsapp.network.ApiService
import com.example.sportsapp.repository.PlayerInfoRepositoryImpl
import com.example.sportsapp.repository.PlayerInfoResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class PlayerInfoRepositoryImplTest {

    private lateinit var repository: PlayerInfoRepositoryImpl
    private lateinit var mockApiService: ApiService
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        mockApiService = mockk()
        repository = PlayerInfoRepositoryImpl(testDispatcher, mockApiService)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testFetchItemsSuccess() = runTest {
        // Mock API response
        val response = PlayersAPIResponse(
            listOf(
                Player(
                    idPlayer = "34173209",
                    strPlayer = "Matt Turner",
                    strTeam = "Arsenal",
                    strSport = "Soccer",
                    strDescriptionEN = "Matthew Charles Turner (born June 24, 1994) is a US professional soccer player who plays as a goalkeeper for Premier League club Arsenal and the United States national team.",
                    strPosition = "Goalkeeper",
                    strTwitter = "twitter.com/headdturnerr",
                    strInstagram = "www.instagram.com/headdturnerr/",
                    strThumb = "https://www.thesportsdb.com/images/media/player/thumb/5tdx8q1678274905.jpg"
                ),
                Player(
                    idPlayer = "34169882",
                    strPlayer = "Reiss Nelson",
                    strTeam = "Arsenal",
                    strSport = "Soccer",
                    strDescriptionEN = "Reiss Luke Nelson (born 10 December 1999) is an English professional footballer who plays as a winger for Premier League club Arsenal.",
                    strPosition = "Forward",
                    strTwitter = "twitter.com/headdturnerr",
                    strInstagram = "www.instagram.com/headdturnerr/",
                    strThumb = "https://www.thesportsdb.com/images/media/player/thumb/5tdx8q1678274905.jpg"
                )
            )
        )
        coEvery { mockApiService.getAllPlayers("Arsenal") }
            .returns(response)


        // Verify the result
        assertEquals(repository.getAllPlayers("Arsenal"),PlayerInfoResult.Success(response) )
    }


    @Test
    fun testFetchItemsFailure() = runTest {
        // Mock API exception
        val exception = Exception("API Error")
        coEvery { mockApiService.getAllPlayers("Arsenal") }
            .throws(exception)

        // Verify the result
        assertEquals(repository.getAllPlayers("Arsenal"), PlayerInfoResult.Failure(exception))
    }

}

