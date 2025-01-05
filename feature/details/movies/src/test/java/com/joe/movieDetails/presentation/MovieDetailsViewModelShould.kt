package com.joe.movieDetails.presentation

import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.movieDetails.resources.MockEntity
import com.joe.movieDetails.resources.MockModel
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
class MovieDetailsViewModelShould {
    private lateinit var viewModel: com.joe.base.presentation.DetailsViewModel
    private val useCase: com.joe.base.usecase.DetailsUseCase = Mockito.mock()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = com.joe.base.presentation.DetailsViewModel(useCase, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `ViewModel is initialized with loading state`() = runTest {
        assertTrue(com.joe.base.presentation.DetailsViewModel.state.value is LoadingState)
    }

    @Test
    fun `set ErrorState when exception is thrown`() = runTest {
        whenever(com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID))
            .thenThrow(NullPointerException())

        com.joe.base.presentation.DetailsViewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = com.joe.base.presentation.DetailsViewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when failure`() = runTest {
        whenever(com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.failure)

        com.joe.base.presentation.DetailsViewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = com.joe.base.presentation.DetailsViewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when null result`() = runTest {
        whenever(com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockModel.nullSuccess)

        com.joe.base.presentation.DetailsViewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = com.joe.base.presentation.DetailsViewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set SuccessState when success`() = runTest {
        whenever(com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.success)

        com.joe.base.presentation.DetailsViewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()

        val state = com.joe.base.presentation.DetailsViewModel.state.value
        assertEquals(com.joe.base.presentation.DetailsSuccessState(MockModel.model), state)
    }

    @Test
    fun `do nothing on refresh when LoadingState`() = runTest {
        whenever(com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.success)

        com.joe.base.presentation.DetailsViewModel.init(MockEntity.MOVIE_ID)
        com.joe.base.presentation.DetailsViewModel.refresh()
        advanceUntilIdle()

        val state = com.joe.base.presentation.DetailsViewModel.state.value
        assertEquals(com.joe.base.presentation.DetailsSuccessState(MockModel.model), state)
        com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID)
    }

    @Test
    fun `do nothing on refresh when RefreshingState && refresh correctly`() = runTest {
        whenever(com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID))
            .thenReturn(MockEntity.success)

        com.joe.base.presentation.DetailsViewModel.init(MockEntity.MOVIE_ID)
        advanceUntilIdle()
        com.joe.base.presentation.DetailsViewModel.refresh()
        com.joe.base.presentation.DetailsViewModel.refresh()
        advanceUntilIdle()

        val state = com.joe.base.presentation.DetailsViewModel.state.value
        assertEquals(com.joe.base.presentation.DetailsSuccessState(MockModel.model), state)
        com.joe.base.usecase.DetailsUseCase.getDetails(MockEntity.MOVIE_ID)
    }

}