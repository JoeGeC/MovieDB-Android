package com.joe.movieDetails.presentation

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.core.entity.MediaDetailsEntity
import com.joe.core.viewModels.CompletedState
import com.joe.core.viewModels.ErrorState
import com.joe.core.viewModels.LoadingState
import com.joe.core.viewModels.RefreshingState
import com.joe.core.viewModels.ViewModelState
import com.joe.movieDetails.domain.usecase.MovieDetailsUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelShould {
    private lateinit var viewModel: MovieDetailsViewModel
    private val movieDetailsUseCase: MovieDetailsUseCase = Mockito.mock()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieDetailsViewModel(movieDetailsUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `ViewModel is initialized with loading state`() = runTest {
        assertTrue(viewModel.state.value is LoadingState)
    }

    @Test
    fun `getMovie emits success state and then completed state`() = runTest {
        whenever(movieDetailsUseCase.getMovieDetails(MockObjects.MOVIE_ID))
            .thenReturn(MockObjects.successFlow)
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }
        viewModel.getMovie(MockObjects.MOVIE_ID)
        advanceUntilIdle()
        job.cancel()

        assertTrue(emittedStates[0] is LoadingState)
        val successState = MovieDetailsSuccessState(MockObjects.model)
        assertEquals(CompletedState(successState), emittedStates[1])
        assertEquals(2, emittedStates.size)
    }

    @Test
    fun `getMovie emits error state and then completed state`() = runTest {
        whenever(movieDetailsUseCase.getMovieDetails(MockObjects.MOVIE_ID))
            .thenReturn(MockObjects.failureFlow)
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }
        viewModel.getMovie(MockObjects.MOVIE_ID)
        advanceUntilIdle()
        job.cancel()

        assertTrue(emittedStates[0] is LoadingState)
        val errorState = ErrorState()
        assertEquals(CompletedState(errorState), emittedStates[1])
        assertEquals(2, emittedStates.size)
    }

    @Test
    fun `getMovie emits error state and then success state and then completed state`() = runTest {
        val flow = MutableSharedFlow<Either<MediaDetailsEntity?, ErrorEntity?>>()
        whenever(movieDetailsUseCase.getMovieDetails(MockObjects.MOVIE_ID)).thenReturn(flow)
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }

        viewModel.getMovie(MockObjects.MOVIE_ID)
        advanceUntilIdle()
        flow.emit(MockObjects.failure)
        advanceUntilIdle()
        flow.emit(MockObjects.success)
        advanceUntilIdle()
        job.cancel()

        println(emittedStates)
        assertEquals(3, emittedStates.size)
        assertTrue(emittedStates[0] is LoadingState)
        assertEquals(ErrorState(), emittedStates[1])
        assertEquals(MovieDetailsSuccessState(MockObjects.model), emittedStates[2])
    }

    @Test
    fun `refresh should not emit new state if current state is LoadingState`() = runTest{
        assertTrue(viewModel.state.value is LoadingState)

        viewModel.refresh(MockObjects.MOVIE_ID)
        advanceUntilIdle()

        assertTrue(viewModel.state.value is LoadingState)
    }

    @Test
    fun `refresh should emit RefreshingState with previous state`() = runTest{
        whenever(movieDetailsUseCase.getMovieDetails(MockObjects.MOVIE_ID))
            .thenReturn(MockObjects.successFlow)
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }
        viewModel.getMovie(MockObjects.MOVIE_ID)
        advanceUntilIdle()
        viewModel.refresh(MockObjects.MOVIE_ID)
        advanceUntilIdle()
        job.cancel()

        assertTrue(emittedStates[0] is LoadingState)
        val successState = MovieDetailsSuccessState(MockObjects.model)
        val completedState = CompletedState(successState)
        assertEquals(completedState, emittedStates[1])
        assertEquals(RefreshingState(completedState), emittedStates[2])
        assertEquals(completedState, emittedStates[3])
        assertEquals(4, emittedStates.size)
    }
}