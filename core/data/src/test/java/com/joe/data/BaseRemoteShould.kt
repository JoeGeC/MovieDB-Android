package com.joe.data

import com.joe.core.entity.Either
import com.joe.data.resources.MockJson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class BaseRemoteTest {
    private val testDispatcher = StandardTestDispatcher()
    private val baseRemote = object : BaseRemote() {}

    @Test
    fun `tryRemote returns Success when call is successful`() = runTest(testDispatcher) {
        val mockCall: Call<String> = mock()
        val expectedResponse = "Success Response"
        whenever(mockCall.execute()).thenReturn(Response.success(expectedResponse))

        val result = baseRemote.tryRemote { mockCall }

        assert(result.isSuccess)
        assertEquals(expectedResponse, (result as Either.Success).value)
    }

    @Test
    fun `tryRemote returns Failure with ErrorResponse when call fails`() = runTest(testDispatcher) {
        val mockCall: Call<String> = mock()
        val errorBody = MockJson.ERROR_JSON.toResponseBody()
        whenever(mockCall.execute()).thenReturn(Response.error(400, errorBody))

        val result = baseRemote.tryRemote { mockCall }

        assert(result is Either.Failure)
        val error = (result as Either.Failure).error
        assertEquals(MockJson.ERROR_MESSAGE, error?.statusMessage)
    }

    @Test
    fun `tryRemote returns Failure with exception message when exception occurs`() = runTest(testDispatcher) {
        val mockCall: Call<String> = mock()
        val exceptionMessage = "Network Error"
        whenever(mockCall.execute()).thenThrow(RuntimeException(exceptionMessage))

        val result = baseRemote.tryRemote { mockCall }

        assert(result.isFailure)
        val error = (result as Either.Failure).error
        assertEquals(exceptionMessage, error?.statusMessage)
    }
}
