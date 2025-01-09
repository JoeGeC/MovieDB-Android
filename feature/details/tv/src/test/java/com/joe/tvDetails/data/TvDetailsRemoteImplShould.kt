package com.joe.tvDetails.data

import com.joe.core.entity.Either
import com.joe.data.json.NetworkProvider
import com.joe.tvDetails.resources.MockEntity
import com.joe.tvDetails.resources.MockJson
import com.joe.tvDetails.resources.MockResponseModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsRemoteImplTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var movieDetailsRemote: TvDetailsRemoteImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        retrofit = NetworkProvider.createRetrofit(mockWebServer.url("/").toString())
        movieDetailsRemote = TvDetailsRemoteImpl(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getMovieDetails returns Success with MovieDetailsResponse`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(MockJson.SUCCESS).setResponseCode(200))

        val result = movieDetailsRemote.getDetails(MockEntity.ID)

        assert(result.isSuccess)
        val data = (result as Either.Success).value
        assertEquals(MockEntity.ID, data?.id)
        assertEquals(MockEntity.NAME, data?.name)
        assertEquals(MockEntity.TAGLINE, data?.tagline)
        assertEquals(MockEntity.OVERVIEW, data?.overview)
        assertEquals(MockResponseModel.FIRST_AIR_DATE, data?.firstAirDate)
        assertEquals(MockResponseModel.LAST_AIR_DATE, data?.lastAirDate)
        assertEquals(MockEntity.POSTER_PATH, data?.posterPath)
        assertEquals(MockEntity.SCORE, data?.voteAverage)
        assertEquals(MockEntity.BACKDROP_PATH, data?.backdropPath)
        assertEquals(MockEntity.NUMBER_OF_EPISODES, data?.numberOfEpisodes)
        assertEquals(MockEntity.NUMBER_OF_SEASONS, data?.numberOfSeasons)
        assertEquals(MockEntity.IN_PRODUCTION, data?.inProduction)
    }

    @Test
    fun `getMovieDetails returns Failure with ErrorResponse`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(MockJson.ERROR_JSON)
                .setResponseCode(MockJson.ERROR_CODE)
                .addHeader("Content-Type", "application/json")
        )

        val result = movieDetailsRemote.getDetails(123)

        assert(result.isFailure)
        val error = (result as Either.Failure).error
        assertEquals(MockJson.ERROR_MESSAGE, error?.statusMessage)
    }
}
