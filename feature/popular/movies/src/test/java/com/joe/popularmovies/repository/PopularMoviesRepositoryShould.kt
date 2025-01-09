package com.joe.popularmovies.repository

import com.joe.popular.data.PopularRemote
import com.joe.popular.local.PopularLocal
import com.joe.popular.repository.PopularRepositoryImpl
import com.joe.popularmovies.local.model.PopularMoviesLocalModel
import com.joe.popularmovies.repository.converter.PopularMoviesRepositoryConverter
import com.joe.popularmovies.repository.response.MovieListItemResponse
import com.joe.popularmovies.repository.response.PopularMoviesResponse
import com.joe.popularmovies.resources.MockLocal
import com.joe.popularmovies.resources.MockEntity
import com.joe.popularmovies.resources.MockResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

class PopularMoviesRepositoryImplTest {
    private var mockRemote: PopularRemote<MovieListItemResponse, PopularMoviesResponse> = mock()
    private var mockLocal: PopularLocal<PopularMoviesLocalModel> = mock()
    private lateinit var repository: PopularRepositoryImpl<PopularMoviesLocalModel, MovieListItemResponse, PopularMoviesResponse>
    private var converter = PopularMoviesRepositoryConverter()

    @Before
    fun setup() {
        repository = PopularRepositoryImpl(mockRemote, mockLocal, converter)
    }

    @Test
    fun `returns local data when fetchLocal succeeds`() = runBlocking {
        whenever(mockLocal.get(MockEntity.PAGE_1)).thenReturn(MockLocal.success)

        val result = repository.getItems(MockEntity.PAGE_1)

        assertEquals(MockEntity.success1, result)
        verify(mockLocal, times(1)).get(MockEntity.PAGE_1)
        verifyNoInteractions(mockRemote)
    }

    @Test
    fun `fetch from remote when fetchLocal fails`() = runBlocking {
        whenever(mockLocal.get(MockEntity.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(mockRemote.getItems(MockEntity.PAGE_1)).thenReturn(MockResponse.success)

        val result = repository.getItems(MockEntity.PAGE_1)

        assertEquals(MockEntity.success1, result)
        verify(mockLocal, times(1)).get(MockEntity.PAGE_1)
        verify(mockRemote, times(1)).getItems(MockEntity.PAGE_1)
        verify(mockLocal, times(1)).insert(MockLocal.model)
    }

    @Test
    fun `getPopularMovies returns failure when both fetchLocal and fetchRemote fail`() = runBlocking {
        whenever(mockLocal.get(MockEntity.PAGE_1)).thenReturn(MockResponse.failure)
        whenever(mockRemote.getItems(MockEntity.PAGE_1)).thenReturn(MockResponse.failure)

        val result = repository.getItems(MockEntity.PAGE_1)

        assertEquals(MockEntity.failure, result)
        verify(mockLocal, times(1)).get(MockEntity.PAGE_1)
        verify(mockRemote, times(1)).getItems(MockEntity.PAGE_1)
        verify(mockLocal, never()).insert(any())
    }
}
