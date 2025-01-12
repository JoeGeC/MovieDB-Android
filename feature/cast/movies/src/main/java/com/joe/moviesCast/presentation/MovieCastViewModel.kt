package com.joe.moviesCast.presentation

import com.joe.cast.domain.CastUseCase
import com.joe.cast.presentation.CastViewModel
import com.joe.cast.presentation.converter.CastPresentationConverter
import com.joe.moviesCast.MovieCast
import com.joe.presentation.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MovieCastViewModel @Inject constructor(
    @MovieCast useCase: CastUseCase,
    converter: CastPresentationConverter,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CastViewModel(useCase, converter, dispatcher)