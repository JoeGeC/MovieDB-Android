package com.joe.popularmovies.presentation

import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.popularmovies.domain.entity.PopularMoviesEntity
import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.resources.MockObjects
import com.joe.presentation.viewModels.CompletedState
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.ViewModelState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesViewModelShould {
    private lateinit var viewModel: PopularMoviesViewModel
    private val useCase: PopularMoviesUseCase = mock()
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
    fun `emit SuccessState and then SuccessState on flow`() = runTest {
        val channel = Channel<Either<PopularMoviesEntity?, ErrorEntity?>>()
        whenever(useCase.getPopularMovies(1)).thenReturn(channel.receiveAsFlow())
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }
        viewModel.init()
        advanceUntilIdle()
        channel.send(MockObjects.success1)
        advanceUntilIdle()
        channel.send(MockObjects.success2)
        advanceUntilIdle()
        channel.close()
        advanceUntilIdle()
        job.cancel()

        assertTrue(emittedStates[0] is LoadingState)
        assertEquals(PopularMoviesSuccessState(MockObjects.popularMoviesModel1), emittedStates[1])
        val successState2 = PopularMoviesSuccessState(MockObjects.popularMoviesModel2)
        assertEquals(successState2, emittedStates[2])
        assertEquals(CompletedState(successState2), emittedStates[3])
        assertEquals(4, emittedStates.size)
    }

    @Test
    fun `emit SuccessState once when identical success flow`() = runTest {
        val channel = Channel<Either<PopularMoviesEntity?, ErrorEntity?>>()
        whenever(useCase.getPopularMovies(1)).thenReturn(channel.receiveAsFlow())
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }
        viewModel.init()
        advanceUntilIdle()
        channel.send(MockObjects.success1)
        advanceUntilIdle()
        channel.send(MockObjects.success1)
        advanceUntilIdle()
        channel.close()
        advanceUntilIdle()
        job.cancel()

        assertTrue(emittedStates[0] is LoadingState)
        val successState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(successState, emittedStates[1])
        assertEquals(CompletedState(successState), emittedStates[2])
        assertEquals(3, emittedStates.size)
    }

    @Test
    fun `emit SuccessState once when success then failure flow`() = runTest {
        val channel = Channel<Either<PopularMoviesEntity?, ErrorEntity?>>()
        whenever(useCase.getPopularMovies(1)).thenReturn(channel.receiveAsFlow())
        val emittedStates = mutableListOf<ViewModelState>()

        val job = launch {
            viewModel.state.collect { state -> emittedStates.add(state) }
        }
        viewModel.init()
        advanceUntilIdle()
        channel.send(MockObjects.success1)
        advanceUntilIdle()
        channel.send(MockObjects.failure)
        advanceUntilIdle()
        channel.close()
        advanceUntilIdle()
        job.cancel()

        assertTrue(emittedStates[0] is LoadingState)
        val successState = PopularMoviesSuccessState(MockObjects.popularMoviesModel1)
        assertEquals(successState, emittedStates[1])
        assertEquals(CompletedState(successState), emittedStates[2])
        assertEquals(3, emittedStates.size)
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
    fun `do nothing on getMoreMovies when not CompletedState`() = runTest {
        whenever(useCase.getPopularMovies(1)).thenReturn(MockObjects.successFlow1)

        viewModel.init()
        assertFalse(viewModel.state.value is CompletedState)
        viewModel.getMoreMovies()
        assertFalse(viewModel.state.value is PopularMoviesLoadingMoreState)
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