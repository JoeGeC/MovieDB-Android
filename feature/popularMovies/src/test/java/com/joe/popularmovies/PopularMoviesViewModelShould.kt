package com.joe.popularmovies

import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.presentation.ErrorState
import com.joe.popularmovies.presentation.LoadingState
import com.joe.popularmovies.presentation.PopularMoviesCompletedState
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
        assertTrue(state is PopularMoviesCompletedState)
        assertTrue(state.getBaseState() is ErrorState)
    }

    @Test
    fun `set ErrorState on failure`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.failureFlow)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is PopularMoviesCompletedState)
        assertTrue(state.getBaseState() is ErrorState)
    }

    @Test
    fun `set SuccessState on success`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is PopularMoviesCompletedState)
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
        assertTrue(state is PopularMoviesCompletedState)
        val expectedState = PopularMoviesSuccessState(MockObjects.popularMoviesModel2)
        assertEquals(expectedState, state.getBaseState())
    }

}