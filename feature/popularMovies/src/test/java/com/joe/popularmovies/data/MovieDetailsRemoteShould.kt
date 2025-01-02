package com.joe.popularmovies.data

import com.joe.core.entity.Either
import com.joe.data.NetworkProvider
import com.joe.popularmovies.resources.MockJson
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
class MovieDetailsRemoteShould {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var movieDetailsRemote: PopularMoviesRemoteImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        retrofit = NetworkProvider.createRetrofit(mockWebServer.url("/").toString())
        movieDetailsRemote = PopularMoviesRemoteImpl(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `return Success`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(MockJson.SUCCESS).setResponseCode(200))

        val result = movieDetailsRemote.getPopularMovies(MockJson.PAGE_1)

        println(result)
        assert(result.isSuccess)
        val data = (result as Either.Success).value
        assertEquals(MockJson.PAGE_1, data?.page)
        assertEquals(MockJson.TOTAL_PAGES, data?.totalPages)

        val movie1 = data?.results?.get(0)
        assertEquals(MockJson.MOVIE_ID_1, movie1?.id)
        assertEquals(MockJson.MOVIE_TITLE_1, movie1?.title)
        assertEquals(MockJson.MOVIE_RELEASE_DATE_1, movie1?.releaseDate)
        assertEquals(MockJson.MOVIE_POSTER_PATH_1, movie1?.posterPath)
        assertEquals(MockJson.MOVIE_SCORE_1, movie1?.voteAverage)
        val movie2 = data?.results?.get(1)
        assertEquals(MockJson.MOVIE_ID_2, movie2?.id)
        assertEquals(MockJson.MOVIE_TITLE_2, movie2?.title)
        assertEquals(MockJson.MOVIE_RELEASE_DATE_2, movie2?.releaseDate)
        assertEquals(MockJson.MOVIE_POSTER_PATH_2, movie2?.posterPath)
        assertEquals(MockJson.MOVIE_SCORE_2, movie2?.voteAverage)
    }

}