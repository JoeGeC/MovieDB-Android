package com.joe.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.Before
import com.joe.data.response.Result

class BaseRemoteShould {
    class TestRemote : BaseRemote()

    private lateinit var testRemote: TestRemote
    private val exceptionMessage = "Test Exception"

    @Before
    fun setup() {
        testRemote = TestRemote()
    }

    @Test
    fun `tryRemote should return Success when remoteCall is successful`() = runBlocking {
        val expectedResult = "Success Result"

        val result = testRemote.tryRemote { expectedResult }

        assertTrue(result.isSuccess)
        assertEquals(expectedResult, (result as Result.Success).value)
    }

    @Test
    fun `tryRemote should return Failure when remoteCall throws an exception`() = runBlocking {
        val result = testRemote.tryRemote<String> {
            throw Exception(exceptionMessage)
        }

        assertTrue(result.isFailure)
        val errorResponse = (result as Result.Failure).error
        assertEquals(exceptionMessage, errorResponse?.statusMessage)
    }

    @Test
    fun `tryRemote should return Failure with empty message when exception message is null`() = runBlocking {
        val result = testRemote.tryRemote<String> {
            throw Exception()
        }

        assertTrue(result.isFailure)
        val errorResponse = (result as Result.Failure).error
        assertEquals("", errorResponse?.statusMessage)
    }
}
