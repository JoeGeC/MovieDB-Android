package com.joe.popularmovies

import com.joe.core.viewModels.ErrorState
import com.joe.core.viewModels.LoadingState
import com.joe.core.viewModels.CompletedState
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.PopularMoviesSuccessState
import com.joe.popularmovies.presentation.PopularMoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesViewModelShould {
    private lateinit var viewModel: PopularMoviesViewModel
    private val useCase: PopularMoviesUseCase = Mockito.mock()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PopularMoviesViewModel(useCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initialize with loading state`() = runTest {
        assertTrue(viewModel.state.value is LoadingState)
    }

    @Test
    fun `set ErrorState on exception`() = runTest {
        whenever(useCase.getPopularMovies(1))
            .thenReturn(flow { throw Exception("Error occurred") })

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        assertTrue(state.getBaseState() is ErrorState)
    }

    @Test
    fun `set ErrorState on failure`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.failureFlow)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        assertTrue(state.getBaseState() is ErrorState)
    }

    @Test
    fun `set ErrorState on empty`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.emptySuccessFlow)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        assertTrue(state.getBaseState() is ErrorState)
    }

    @Test
    fun `set SuccessState on success`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(expectedState, state.getBaseState())
    }

    @Test
    fun `append movies on pagination call`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)
        whenever(useCase.getPopularMovies(2)).thenReturn(MockObjects.successFlow2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel2)
        assertEquals(expectedState, state.getBaseState())
    }

    @Test
    fun `set previous SuccessState when pagination call fails`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)
        whenever(useCase.getPopularMovies(2)).thenReturn(MockObjects.failureFlow)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(expectedState, state.getBaseState())
    }

    @Test
    fun `do nothing on getMoreMovies when !canLoadMore`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)
        whenever(useCase.getPopularMovies(2)).thenReturn(MockObjects.successFlow2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel2)
        assertEquals(expectedState, state.getBaseState())
        verify(useCase, times(2)).getPopularMovies(any())
    }

    @Test
    fun `do nothing on getMoreMovies when not SuccessState`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)

        viewModel.init()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(expectedState, state.getBaseState())
        verify(useCase, times(1)).getPopularMovies(any())
    }

    @Test
    fun `not refresh if is LoadingState`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)

        viewModel.init()
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(expectedState, state.getBaseState())
        verify(useCase, times(1)).getPopularMovies(any())
    }

    @Test
    fun `not refresh if is RefreshingState && refreshes correctly`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)
        whenever(useCase.getPopularMovies(2)).thenReturn(MockObjects.successFlow2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.refresh()
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is CompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(expectedState, state.getBaseState())
        verify(useCase, times(2)).getPopularMovies(any())
    }

}