package com.joe.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.launch

fun ViewModel.job(
    block: suspend CoroutineScope.() -> Unit,
    coroutineDispatcher: CoroutineDispatcher
): Job = viewModelScope.launch(coroutineDispatcher) {
    block()
}