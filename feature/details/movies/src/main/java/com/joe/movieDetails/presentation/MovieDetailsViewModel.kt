package com.joe.movieDetails.presentation

import com.joe.base.presentation.DetailsViewModel
import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.base.usecase.DetailsUseCase
import com.joe.movieDetails.MovieDetails
import com.joe.movieDetails.domain.MovieDetailsEntity
import com.joe.movieDetails.presentation.model.MovieDetailsModel
import com.joe.presentation.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    @MovieDetails useCase: DetailsUseCase<MovieDetailsEntity>,
    @MovieDetails converter: DetailsPresentationConverter<MovieDetailsModel, MovieDetailsEntity>,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : DetailsViewModel<MovieDetailsModel, MovieDetailsEntity>(useCase, converter, dispatcher)
