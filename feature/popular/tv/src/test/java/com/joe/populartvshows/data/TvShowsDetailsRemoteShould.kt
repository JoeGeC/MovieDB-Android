package com.joe.populartvshows.data

import com.joe.core.entity.Either
import com.joe.data.json.NetworkProvider
import com.joe.populartvshows.resources.MockJson
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
class TvShowDetailsRemoteShould {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var tvShowDetailsRemote: PopularTvShowsRemoteImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        retrofit = NetworkProvider.createRetrofit(mockWebServer.url("/").toString())
        tvShowDetailsRemote = PopularTvShowsRemoteImpl(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `return Success`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(MockJson.SUCCESS).setResponseCode(200))

        val result = tvShowDetailsRemote.getItems(MockJson.PAGE_1)

        println(result)
        assert(result.isSuccess)
        val data = (result as Either.Success).value
        assertEquals(MockJson.PAGE_1, data?.page)
        assertEquals(MockJson.TOTAL_PAGES, data?.totalPages)

        val tvShow1 = data?.results?.get(0)
        assertEquals(MockJson.ID_1, tvShow1?.id)
        assertEquals(MockJson.TITLE_1, tvShow1?.name)
        assertEquals(MockJson.FIRST_AIR_DATE_1, tvShow1?.firstAirDate)
        assertEquals(MockJson.POSTER_PATH_1, tvShow1?.posterPath)
        assertEquals(MockJson.SCORE_1, tvShow1?.voteAverage)
        val tvShow2 = data?.results?.get(1)
        assertEquals(MockJson.ID_2, tvShow2?.id)
        assertEquals(MockJson.TITLE_2, tvShow2?.name)
        assertEquals(MockJson.FIRST_AIR_DATE_2, tvShow2?.firstAirDate)
        assertEquals(MockJson.POSTER_PATH_2, tvShow2?.posterPath)
        assertEquals(MockJson.SCORE_2, tvShow2?.voteAverage)
    }

}