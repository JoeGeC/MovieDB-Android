package com.joe.cast.presentation

import androidx.lifecycle.ViewModel
import com.joe.cast.domain.CastUseCase
import com.joe.cast.domain.entity.CastListEntity
import com.joe.cast.presentation.converter.CastPresentationConverter
import com.joe.cast.presentation.state.CastSuccessState
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.presentation.IoDispatcher
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.ViewModelState
import com.joe.presentation.viewModels.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CastViewModel(
    private val useCase: CastUseCase,
    private val converter: CastPresentationConverter,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _state = MutableStateFlow<ViewModelState>(LoadingState())
    val state: StateFlow<ViewModelState> = _state.asStateFlow()

    fun getCastOf(mediaId: Int?){
        if(mediaId == null){
            _state.value = ErrorState()
            return
        }
        job({
            _state.value = LoadingState()
            try {
                val result = useCase.getCastOf(mediaId)
                handleResult(result)
            } catch (_: Exception){
                _state.value = ErrorState()
            }
        }, dispatcher)
    }

    private fun handleResult(result: Either<CastListEntity?, ErrorEntity?>) = when {
        result.isSuccess -> handleSuccessState(result)
        else -> _state.value = ErrorState()
    }

    private fun handleSuccessState(result: Either<CastListEntity?, ErrorEntity?>) {
        val body = result.body
        _state.value = if (body != null) {
            CastSuccessState(converter.entityToModel(body))
        } else ErrorState()
    }
}