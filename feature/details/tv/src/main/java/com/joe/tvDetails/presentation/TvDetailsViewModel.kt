package com.joe.tvDetails.presentation

import com.joe.base.presentation.DetailsViewModel
import com.joe.base.presentation.converter.DetailsPresentationConverter
import com.joe.base.usecase.DetailsUseCase
import com.joe.presentation.IoDispatcher
import com.joe.tvDetails.TvDetails
import com.joe.tvDetails.domain.TvDetailsEntity
import com.joe.tvDetails.presentation.model.TvDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    @TvDetails useCase: DetailsUseCase<TvDetailsEntity>,
    @TvDetails converter: DetailsPresentationConverter<TvDetailsModel, TvDetailsEntity>,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : DetailsViewModel<TvDetailsModel, TvDetailsEntity>(useCase, converter, dispatcher)
