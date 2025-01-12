package com.joe.tvCast.presentation

import com.joe.cast.domain.CastUseCase
import com.joe.cast.presentation.converter.CastPresentationConverterImpl
import com.joe.cast.presentation.state.CastSuccessState
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.tvCast.resources.MockEntity
import com.joe.tvCast.resources.MockModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class TvCastViewModelShould {
    private lateinit var viewModel: TvCastViewModel
    private val useCase: CastUseCase = mock()
    private val converter = CastPresentationConverterImpl()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TvCastViewModel(useCase, converter, testDispatcher)
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
    fun `set ErrorState when exception is thrown`() = runTest {
        whenever(useCase.getCastOf(MockEntity.CAST_LIST_ID))
            .thenThrow(NullPointerException())

        viewModel.getCastOf(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when failure`() = runTest {
        whenever(useCase.getCastOf(MockEntity.CAST_LIST_ID))
            .thenReturn(MockEntity.failure)

        viewModel.getCastOf(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set ErrorState when null result`() = runTest {
        whenever(useCase.getCastOf(MockEntity.CAST_LIST_ID))
            .thenReturn(MockEntity.nullSuccess)

        viewModel.getCastOf(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ErrorState)
    }

    @Test
    fun `set SuccessState when success`() = runTest {
        whenever(useCase.getCastOf(MockEntity.CAST_LIST_ID))
            .thenReturn(MockEntity.success)

        viewModel.getCastOf(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(CastSuccessState(MockModel.model), state)
    }

    @Test
    fun `do nothing on refresh when LoadingState`() = runTest {
        whenever(useCase.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(MockEntity.success)

        viewModel.getCastOf(MockEntity.CAST_LIST_ID)
        viewModel.refresh(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(CastSuccessState(MockModel.model), state)
        verify(useCase, times(1)).getCastOf(MockEntity.CAST_LIST_ID)
    }

    @Test
    fun `do nothing on refresh when RefreshingState && refresh correctly`() = runTest {
        whenever(useCase.getCastOf(MockEntity.CAST_LIST_ID)).thenReturn(MockEntity.success)

        viewModel.getCastOf(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()
        viewModel.refresh(MockEntity.CAST_LIST_ID)
        viewModel.refresh(MockEntity.CAST_LIST_ID)
        advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(CastSuccessState(MockModel.model), state)
        verify(useCase, times(2)).getCastOf(MockEntity.CAST_LIST_ID)
    }

}