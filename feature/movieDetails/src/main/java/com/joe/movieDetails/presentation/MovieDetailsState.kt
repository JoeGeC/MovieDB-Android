package com.joe.movieDetails.presentation

import com.joe.core.model.MediaDetailsModel
import com.joe.core.viewModels.ViewModelState

data class MovieDetailsSuccessState(val movieDetails: MediaDetailsModel) : ViewModelState()