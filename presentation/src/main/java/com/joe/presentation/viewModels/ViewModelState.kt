package com.joe.presentation.viewModels

abstract class ViewModelState() {
    open fun getBaseState(): ViewModelState = this
}

class LoadingState() : ViewModelState()

class ErrorState() : ViewModelState() {
    override fun equals(other: Any?): Boolean {
        return other is ErrorState
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

data class RefreshingState(
    val previousState: ViewModelState
) : ViewModelState() {
    override fun getBaseState() = previousState
}

data class CompletedState(
    val previousState: ViewModelState
) : ViewModelState() {
    override fun getBaseState() = previousState
}