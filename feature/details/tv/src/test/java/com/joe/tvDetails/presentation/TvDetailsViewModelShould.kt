package com.joe.tvDetails.presentation

import com.joe.base.presentation.DetailsSuccessState
import com.joe.base.usecase.DetailsUseCase
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.presentation.converter.TvDetailsPresentationConverter
import com.joe.tvDetails.presentation.model.TvDetailsModel
import com.joe.tvDetails.resources.MockEntity
import com.joe.tvDetails.resources.MockModel
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
class TvDetailsViewModelShould {
    private lateinit var viewModel: TvDetailsViewModel
    private val useCase: DetailsUseCase<TvDetailsEntity> = Mockito.mock()
    private val converter = TvDetailsPresentationConverter()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TvDetailsViewModel(useCase, converter, testDispatcher)
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
        whenever(useCase.getDetails(MockEntity.ID))
            .thenThrow(NullPointerException())

        viewModel.init(MockEntity.ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when failure`() = runTest {
        whenever(useCase.getDetails(MockEntity.ID))
            .thenReturn(MockEntity.failure)

        viewModel.init(MockEntity.ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when null result`() = runTest {
        whenever(useCase.getDetails(MockEntity.ID))
            .thenReturn(MockModel.nullSuccess)

        viewModel.init(MockEntity.ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set SuccessState when success`() = runTest {
        whenever(useCase.getDetails(MockEntity.ID))
            .thenReturn(MockEntity.success)

        viewModel.init(MockEntity.ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(DetailsSuccessState<TvDetailsModel>(MockModel.model), state)
    }

    @Test
    fun `do nothing on refresh when LoadingState`() = runTest {
        whenever(useCase.getDetails(MockEntity.ID))
            .thenReturn(MockEntity.success)

        viewModel.init(MockEntity.ID)
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(DetailsSuccessState<TvDetailsModel>(MockModel.model), state)
        useCase.getDetails(MockEntity.ID)
    }

    @Test
    fun `do nothing on refresh when RefreshingState && refresh correctly`() = runTest {
        whenever(useCase.getDetails(MockEntity.ID))
            .thenReturn(MockEntity.success)

        viewModel.init(MockEntity.ID)
        advanceUntilIdle()
        viewModel.refresh()
        viewModel.refresh()
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(DetailsSuccessState<TvDetailsModel>(MockModel.model), state)
        useCase.getDetails(MockEntity.ID)
    }

}