package com.example.desafiopicpay.network.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class UiStateLiveData<T> : MutableLiveData<UiState<T>>() {

    private val sourceLiveData = MediatorLiveData<UiState<Any>>()

    private val sourceObserver: (Any?) -> Unit = {}

    fun <R> swapSource(source: LiveData<UiState<R>>, transformation: (R) -> T) {
        sourceLiveData.addSource(source) {

            val newValue = getUiState(it, transformation)

            postValue(newValue)
        }
    }

    fun <R> swapSource(
        source: LiveData<UiState<R>>, converter: UiTransformer<R, T>): UiStateLiveData<T> {
        swapSource(source) {
            converter.map(it)
        }

        return this
    }

    private fun <R> getUiState(it: UiState<R>?, transformation: (R) -> T): UiState<T> {
        return when (it) {
            is UiSuccess<R> -> UiSuccess(transformation(it.data))
            is UiLoading -> UiLoading
            is UiError -> UiError(it.errorData)
            else -> throw IllegalArgumentException("Invalid UiState")
        }
    }

    override fun onInactive() {
        super.onInactive()
        sourceLiveData.removeObserver(sourceObserver)
    }

    override fun onActive() {
        super.onActive()
        sourceLiveData.observeForever(sourceObserver)
    }
}
