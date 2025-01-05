package com.joe.movieDetails.presentation

import com.joe.presentation.model.MediaDetailsModel
import com.joe.presentation.viewModels.ViewModelState

data class MovieDetailsSuccessState(val movieDetails: MediaDetailsModel) : ViewModelState()