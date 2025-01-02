package com.joe.popularmovies.presentation

import com.joe.popularmovies.domain.usecase.PopularMoviesUseCase
import com.joe.popularmovies.resources.MockObjects
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
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
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMoviesViewModelTest {
    private lateinit var viewModel: PopularMoviesViewModel
    private var useCase: PopularMoviesUseCase = mock()
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
    fun `start on LoadingState`() = runTest {
        assertTrue(viewModel.state.value is LoadingState)
    }

    @Test
    fun `emit success state when movies are successfully fetched`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        val successState = state as PopularMoviesSuccessState
        assertEquals(successState.popularMoviesModel, MockObjects.popularMoviesModel1)
    }

    @Test
    fun `emit error state when movies fetch fails`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.failure)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assert(state is ErrorState)
    }

    @Test
    fun `emit error state when movies fetch returns null entity`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.nullSuccess)

        viewModel.init()
        advanceUntilIdle()

        val state = viewModel.state.value
        assert(state is ErrorState)
    }

    @Test
    fun `emit SuccessState state when movies fetch returns null entity but previously was success`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)
        whenever(useCase.getPopularMovies(MockObjects.PAGE_2)).thenReturn(MockObjects.nullSuccess)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(state.popularMoviesModel, MockObjects.popularMoviesModel1)
    }

    @Test
    fun `emit LoadingMoreState when more movies are being loaded`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)
        whenever(useCase.getPopularMovies(MockObjects.PAGE_2)).thenReturn(MockObjects.success2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()

        val state = viewModel.state.value
        assert(state is PopularMoviesLoadingMoreState)
    }

    @Test
    fun `emit SuccessState when more movies are loaded`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)
        whenever(useCase.getPopularMovies(MockObjects.PAGE_2)).thenReturn(MockObjects.success2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(state.popularMoviesModel, MockObjects.popularMoviesModel2)
    }

    @Test
    fun `not get more movies if already getting more`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)
        whenever(useCase.getPopularMovies(MockObjects.PAGE_2)).thenReturn(MockObjects.success2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(state.popularMoviesModel, MockObjects.popularMoviesModel2)
        verify(useCase, times(2)).getPopularMovies(any())
    }

    @Test
    fun `not get more movies if refreshing`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)

        viewModel.init()
        advanceUntilIdle()
        viewModel.refresh()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(state.popularMoviesModel, MockObjects.popularMoviesModel1)
        verify(useCase, times(2)).getPopularMovies(MockObjects.PAGE_1)
    }

    @Test
    fun `reset and emit refreshing state when refresh is triggered`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)

        viewModel.init()
        advanceUntilIdle()
        viewModel.refresh()

        val state = viewModel.state.value
        assert(state is RefreshingState)
        val baseState = state.getBaseState() as PopularMoviesSuccessState
        assertEquals(baseState.popularMoviesModel, MockObjects.popularMoviesModel1)
        advanceUntilIdle()
        val newState = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(newState.popularMoviesModel, MockObjects.popularMoviesModel1)
    }

    @Test
    fun `not refresh when refresh is already triggered`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)

        viewModel.init()
        advanceUntilIdle()
        viewModel.refresh()
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(state.popularMoviesModel, MockObjects.popularMoviesModel1)
        verify(useCase, times(2)).getPopularMovies(MockObjects.PAGE_1)
    }

    @Test
    fun `refresh even if loading more`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        viewModel.refresh()

        val state = viewModel.state.value
        assert(state is RefreshingState)
    }

    @Test
    fun `not fetch more movies if can't load more`() = runTest {
        whenever(useCase.getPopularMovies(MockObjects.PAGE_1)).thenReturn(MockObjects.success1)
        whenever(useCase.getPopularMovies(MockObjects.PAGE_2)).thenReturn(MockObjects.success2)

        viewModel.init()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()
        viewModel.getMoreMovies()
        advanceUntilIdle()

        val state = viewModel.state.value as PopularMoviesSuccessState
        assertEquals(state.popularMoviesModel, MockObjects.popularMoviesModel2)
        verify(useCase, times(2)).getPopularMovies(any())
    }
}
