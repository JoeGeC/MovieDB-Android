package com.joe.base.presentation

import androidx.lifecycle.ViewModel
import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.base.usecase.DetailsUseCase
import com.joe.core.entity.Either
import com.joe.core.entity.ErrorEntity
import com.joe.presentation.IoDispatcher
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState
import com.joe.presentation.viewModels.job
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class DetailsViewModel<Model: MediaDetailsModel, Entity>(
    private val useCase: DetailsUseCase<Entity>,
    private val converter: DetailsPresentationConverter<Model, Entity>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _state = MutableStateFlow<ViewModelState>(LoadingState())
    val state: StateFlow<ViewModelState> = _state.asStateFlow()
    private var mediaId: Int? = null

    fun init(mediaId: Int?) {
        this.mediaId = mediaId
        getDetails()
    }

    private fun getDetails(){
        if(mediaId == null){
            _state.value = ErrorState()
            return
        }
        job({
            _state.value = LoadingState()
            try {
                val result = useCase.getDetails(mediaId!!)
                handleResult(result)
            } catch (_: Exception){
                _state.value = ErrorState()
            }
        }, dispatcher)
    }

    private fun handleResult(result: Either<Entity?, ErrorEntity?>) = when {
        result.isSuccess -> handleSuccessState(result)
        else -> _state.value = ErrorState()
    }

    private fun handleSuccessState(result: Either<Entity?, ErrorEntity?>) {
        val body = result.body
        _state.value = if (body != null) {
            DetailsSuccessState(converter.entityToModel(body))
        } else ErrorState()
    }

    fun refresh() {
        if (_state.value is LoadingState || _state.value is RefreshingState) return
        _state.value = RefreshingState(_state.value)
        getDetails()
    }

}