package com.joe.popularmovies.repository

import com.joe.popularmovies.repository.boundary.PopularMoviesLocal
import com.joe.popularmovies.repository.boundary.PopularMoviesRemote
import com.joe.popularmovies.resources.MockLocal
import com.joe.popularmovies.resources.MockObjects
import com.joe.popularmovies.resources.MockResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

class PopularMoviesRepositoryImplTest {
    private var mockRemote: PopularMoviesRemote = mock()
    private var mockLocal: PopularMoviesLocal = mock()
    private lateinit var repository: PopularMoviesRepositoryImpl

    @Before
    fun setup() {
        repository = PopularMoviesRepositoryImpl(mockRemote, mockLocal)
    }

    @Test
    fun `returns local data when fetchLocal succeeds`() = runBlocking {
        whenever(mockLocal.get(MockObjects.PAGE_1)).thenReturn(MockLocal.success)

        val result = repository.getPopularMovies(1)

        assertEquals(MockObjects.success1, result)
        verify(mockLocal, times(1)).get(MockObjects.PAGE_1)
        verifyNoInteractions(mockRemote)
    }

    @Test
    fun `fetch from remote when fetchLocal fails`() = runBlocking {
        whenever(mockLocal.get(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(mockRemote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.success)

        val result = repository.getPopularMovies(MockObjects.PAGE_1)

        assertEquals(MockObjects.success1, result)
        verify(mockLocal, times(1)).get(MockObjects.PAGE_1)
        verify(mockRemote, times(1)).getPopularMovies(MockObjects.PAGE_1)
        verify(mockLocal, times(1)).insert(MockLocal.model)
    }

    @Test
    fun `getPopularMovies returns failure when both fetchLocal and fetchRemote fail`() = runBlocking {
        whenever(mockLocal.get(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(mockRemote.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockResponse.failure)

        val result = repository.getPopularMovies(MockObjects.PAGE_1)

        assertEquals(MockObjects.failure, result)
        verify(mockLocal, times(1)).get(MockObjects.PAGE_1)
        verify(mockRemote, times(1)).getPopularMovies(MockObjects.PAGE_1)
        verify(mockLocal, never()).insert(any())
    }
}
