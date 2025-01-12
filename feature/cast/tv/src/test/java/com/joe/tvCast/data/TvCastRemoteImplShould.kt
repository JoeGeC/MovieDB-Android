package com.joe.tvCast.data

import com.joe.core.entity.Either
import com.joe.data.json.NetworkProvider
import com.joe.tvCast.resources.MockEntity
import com.joe.tvCast.resources.MockJson
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
class TvCastRemoteImplShould {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var retrofit: Retrofit
    private lateinit var tvCastRemote: TvCastRemoteImpl

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        retrofit = NetworkProvider.createRetrofit(mockWebServer.url("/").toString())
        tvCastRemote = TvCastRemoteImpl(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `return Success`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(MockJson.SUCCESS).setResponseCode(200))

        val result = tvCastRemote.getCastOf(MockEntity.CAST_LIST_ID)

        assert(result.isSuccess)
        val data = (result as Either.Success).value
        assertEquals(MockEntity.CAST_LIST_ID, data?.id)
        val actor1 = data?.cast[0]
        assertEquals(MockEntity.ACTOR_ID_1, actor1?.id)
        assertEquals(MockEntity.ACTOR_NAME_1, actor1?.name)
        assertEquals(MockEntity.ACTOR_CHARACTER_1, actor1?.character)
        assertEquals(null, actor1?.profilePath)
        val actor2 = data?.cast[1]
        assertEquals(MockEntity.ACTOR_ID_2, actor2?.id)
        assertEquals(MockEntity.ACTOR_NAME_2, actor2?.name)
        assertEquals(MockEntity.ACTOR_CHARACTER_2, actor2?.character)
        assertEquals(MockEntity.ACTOR_PROFILE_PATH_2, actor2?.profilePath)
        val crew1 = data?.crew[0]
        assertEquals(MockEntity.CREW_PERSON_ID_1, crew1?.id)
        assertEquals(MockEntity.CREW_NAME_1, crew1?.name)
        assertEquals(MockEntity.CREW_JOB_1, crew1?.job)
        assertEquals(MockEntity.CREW_PROFILE_PATH_1, crew1?.profilePath)
        val crew2 = data?.crew[1]
        assertEquals(MockEntity.CREW_PERSON_ID_2, crew2?.id)
        assertEquals(MockEntity.CREW_NAME_2, crew2?.name)
        assertEquals(MockEntity.CREW_JOB_2, crew2?.job)
        assertEquals(null, crew2?.profilePath)
    }

    @Test
    fun `getMovieDetails returns Failure with ErrorResponse`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(MockJson.ERROR_JSON)
                .setResponseCode(MockJson.ERROR_CODE)
                .addHeader("Content-Type", "application/json")
        )

        val result = tvCastRemote.getCastOf(5)

        assert(result.isFailure)
        val error = (result as Either.Failure).error
        assertEquals(MockJson.ERROR_MESSAGE, error?.statusMessage)
    }
}
