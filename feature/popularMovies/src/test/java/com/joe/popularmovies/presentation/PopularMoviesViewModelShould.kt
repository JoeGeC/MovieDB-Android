package com.joe.popularmovies.presentation

import android.net.http.NetworkException
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import app.cash.turbine.test
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.resources.MockObjects
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.Int

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesViewModelTest {
    private lateinit var viewModel: PopularMoviesViewModel
    private var useCase: PopularMoviesUseCase = mock()

    @Before
    fun setup() {
        viewModel = PopularMoviesViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load returns LoadResult_Page on successful data load`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)

        val pagingSource = PopularMoviesPagingSource(useCase)

        val params = PagingSource.LoadParams.Refresh<Int>(null, 20, false)
        val result = pagingSource.load(params)

        assertTrue(result is LoadResult.Page)
        val page = result as LoadResult.Page
        assertEquals(MockObjects.modelList1, page.data)
        assertEquals(null, page.prevKey)
        assertEquals(2, page.nextKey)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `load returns second page with null next key on final page`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)
        whenever(useCase.getPopularMovies(MockObjects.PAGE_2)).thenReturn(MockObjects.success2)

        val pagingSource = PopularMoviesPagingSource(useCase)

        val params1 = PagingSource.LoadParams.Refresh<Int>(null, 20, false)
        pagingSource.load(params1)
        val params2 = PagingSource.LoadParams.Refresh<Int>(2, 20, false)
        val result = pagingSource.load(params2)

        assertTrue(result is LoadResult.Page)
        val page = result as LoadResult.Page
        assertEquals(MockObjects.modelList2, page.data)
        assertEquals(1, page.prevKey)
        assertEquals(null, page.nextKey)
    }

    @Test
    fun `load returns LoadResult_Error on use case failure`() = runTest {
        whenever(useCase.getPopularMovies(any())).thenThrow(NullPointerException("Network error"))

        val pagingSource = PopularMoviesPagingSource(useCase)

        val params = PagingSource.LoadParams.Refresh<Int>(null, 20, false)
        val result = pagingSource.load(params)

        assertTrue(result is LoadResult.Error)
        val error = result as LoadResult.Error
        assertEquals("Network error", error.throwable.message)
    }

}
