package com.example.desafiopicpay.network.ui

import androidx.lifecycle.LiveData

abstract class UseCaseUiState<in P, R, T> {

    operator fun invoke(parameters: P, result: UiStateLiveData<R>) {
        create(parameters).let { useCaseResponse ->
            result.swapSource(useCaseResponse) {
                map(it)
            }
        }
    }
    protected abstract fun create(parameters: P): LiveData<UiState<T>>
    protected abstract fun map(it: T): R
}
