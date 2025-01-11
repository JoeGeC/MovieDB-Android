package com.joe.cast.presentation.state

import com.joe.cast.presentation.model.CastListModel
import com.joe.presentation.viewModels.ViewModelState

data class CastSuccessState(
    val castListModel: CastListModel
): ViewModelState()