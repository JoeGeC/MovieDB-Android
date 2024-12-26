package com.joe.movieDetails.data

import com.joe.core.entity.Either
import com.joe.data.NetworkProvider
import com.joe.movieDetails.resources.MockJson
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
    private lateinit var movieDetailsRemote: MovieDetailsRemoteImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        retrofit = NetworkProvider.createRetrofit(mockWebServer.url("/").toString())
        movieDetailsRemote = MovieDetailsRemoteImpl(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getMovieDetails returns Success with MovieDetailsResponse`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(MockJson.SUCCESS).setResponseCode(200))

        val result = movieDetailsRemote.getMovieDetails(MockJson.MOVIE_ID)

        assert(result.isSuccess)
        val data = (result as Either.Success).value
        assertEquals(MockJson.MOVIE_ID, data?.id)
        assertEquals(MockJson.MOVIE_TITLE, data?.title)
        assertEquals(MockJson.MOVIE_TAGLINE, data?.tagline)
        assertEquals(MockJson.MOVIE_OVERVIEW, data?.overview)
        assertEquals(MockJson.MOVIE_RELEASE_DATE, data?.releaseDate)
        assertEquals(MockJson.MOVIE_POSTER_PATH, data?.posterPath)
        assertEquals(MockJson.MOVIE_VOTE_AVERAGE, data?.voteAverage)
        assertEquals(MockJson.MOVIE_BACKDROP_PATH, data?.backdropPath)
    }

    @Test
    fun `getMovieDetails returns Failure with ErrorResponse`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(MockJson.ERROR_JSON)
                .setResponseCode(MockJson.ERROR_CODE)
                .addHeader("Content-Type", "application/json")
        )

        val result = movieDetailsRemote.getMovieDetails(123)

        assert(result.isFailure)
        val error = (result as Either.Failure).error
        assertEquals(MockJson.ERROR_MESSAGE, error?.statusMessage)
    }
}
