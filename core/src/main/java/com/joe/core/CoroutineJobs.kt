package com.joe.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.launch

var ioCoroutineDispatcher: CoroutineDispatcher = Dispatchers.IO

fun ViewModel.ioJob(block: suspend CoroutineScope.() -> Unit): Job =
    viewModelScope.launch(ioCoroutineDispatcher) {
        block()
    }

fun ViewModel.job(
    block: suspend CoroutineScope.() -> Unit,
    coroutineDispatcher: CoroutineDispatcher
): Job = viewModelScope.launch(coroutineDispatcher) {
    block()
}