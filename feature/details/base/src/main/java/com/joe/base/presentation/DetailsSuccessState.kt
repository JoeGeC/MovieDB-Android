package com.joe.base.presentation

import com.joe.presentation.viewModels.ViewModelState

data class DetailsSuccessState<Model: MediaDetailsModel>(val mediaDetails: Model) : ViewModelState()