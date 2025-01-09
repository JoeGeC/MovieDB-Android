package com.joe.movieDetails.presentation

import com.joe.base.presentation.DetailsSuccessState
import com.joe.base.usecase.DetailsUseCase
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.presentation.converter.MovieDetailsPresentationConverter
import com.joe.movieDetails.presentation.model.MovieDetailsModel
import com.joe.movieDetails.resources.MockEntity
import com.joe.movieDetails.resources.MockModel
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class MovieviewModelShould {
    private lateinit var viewModel: MovieDetailsViewModel
    private val useCase: DetailsUseCase<MovieDetailsEntity> = Mockito.mock()
    private val converter = MovieDetailsPresentationConverter()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieDetailsViewModel(useCase, converter, testDispatcher)
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
    fun `set ErrorState when exception is thrown`() = runTest {
        whenever(useCase.getDetails(MockEntity.MOVIE_ID))
            .thenThrow(NullPointerException())

        viewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when failure`() = runTest {
        whenever(useCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.failure)

        viewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when null result`() = runTest {
        whenever(useCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockModel.nullSuccess)

        viewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set SuccessState when success`() = runTest {
        whenever(useCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.success)

        viewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(DetailsSuccessState<MovieDetailsModel>(MockModel.model), state)
    }

    @Test
    fun `do nothing on refresh when LoadingState`() = runTest {
        whenever(useCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.success)

        viewModel.init(MockEntity.MOVIE_ID)
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(DetailsSuccessState<MovieDetailsModel>(MockModel.model), state)
        useCase.getDetails(MockEntity.MOVIE_ID)
    }

    @Test
    fun `do nothing on refresh when RefreshingState && refresh correctly`() = runTest {
        whenever(useCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.success)

        viewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()
        viewModel.refresh()
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(DetailsSuccessState<MovieDetailsModel>(MockModel.model), state)
        useCase.getDetails(MockEntity.MOVIE_ID)
    }

}