package com.joe.movieDetails.local

import com.joe.core.entity.Either
import com.joe.data.response.ErrorResponse
import com.joe.movieDetails.resources.MockEntity
import com.joe.movieDetails.resources.MockLocal
import com.joe.movieDetails.resources.MockResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsLocalShould {
    private lateinit var local: MovieDetailsLocalImpl
    private val mockDatabase: MovieDatabase = mock()
    private val mockDao: MovieDetailsDao = mock()

    @Before
    fun setup() {
        whenever(mockDatabase.movieDetailsDao()).thenReturn(mockDao)
        local = MovieDetailsLocalImpl(mockDatabase)
    }

    @Test
    fun `insert should call dao insertAll with correct data`() = runTest {
        local.insert(MockResponse.response)

        verify(mockDao).insertAll(MockLocal.model)
    }

    @Test
    fun `insert should handle exceptions gracefully`() = runTest {
        whenever(mockDao.insertAll(any())).thenThrow(RuntimeException("Database error"))

        local.insert(MockResponse.response)

        verify(mockDao).insertAll(any())
    }

    @Test
    fun `getMovieDetails should return success when dao returns data`() = runTest {
        whenever(mockDao.getById(MockEntity.MOVIE_ID)).thenReturn(MockLocal.model)

        val result = local.getMovieDetails(MockEntity.MOVIE_ID)

        assertTrue(result is Either.Success)
        assertEquals(MockResponse.response, result.body)
    }

    @Test
    fun `getMovieDetails should return failure when dao returns null`() = runTest {
        whenever(mockDao.getById(MockEntity.MOVIE_ID)).thenReturn(null)

        val result = local.getMovieDetails(MockEntity.MOVIE_ID)

        assertTrue(result is Either.Failure)
        assertTrue(result.errorBody is ErrorResponse)
    }
}