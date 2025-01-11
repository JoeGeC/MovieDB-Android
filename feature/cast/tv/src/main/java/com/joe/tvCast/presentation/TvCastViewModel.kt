package com.joe.tvCast.presentation

import com.joe.cast.domain.CastUseCase
import com.joe.cast.presentation.CastViewModel
import com.joe.cast.presentation.converter.CastPresentationConverter
import com.joe.presentation.IoDispatcher
import com.joe.tvCast.TvCast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class TvCastViewModel @Inject constructor(
    @TvCast useCase: CastUseCase,
    converter: CastPresentationConverter,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CastViewModel(useCase, converter, dispatcher)