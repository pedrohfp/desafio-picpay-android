package com.example.desafiopicpay.network.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <RESPONSE> LiveData<UiState<RESPONSE>>.observeOnLoading(
    owner: LifecycleOwner, observer: () -> Unit): LiveData<UiState<RESPONSE>> {
    observe(owner, Observer {
        if (it is UiLoading) observer.invoke()
    })
    return this
}

fun <RESPONSE> LiveData<UiState<RESPONSE>>.observeOnSuccess(
    owner: LifecycleOwner, observer: (RESPONSE) -> Unit): LiveData<UiState<RESPONSE>> {
    observe(owner, Observer {
        if (it is UiSuccess) observer.invoke(it.data)
    })
    return this
}

fun <RESPONSE> LiveData<UiState<RESPONSE>>.observeOnSuccess(
    owner: LifecycleOwner, observer: () -> Unit): LiveData<UiState<RESPONSE>> {
    observe(owner, Observer {
        if (it is UiSuccess) observer.invoke()
    })
    return this
}

fun <RESPONSE> LiveData<UiState<RESPONSE>>.observeOnError(
    owner: LifecycleOwner, observer: (ErrorData) -> Unit): LiveData<UiState<RESPONSE>> {
    observe(owner, Observer {
        if (it is UiError) observer.invoke(it.errorData)
    })
    return this
}

fun <T> UiStateLiveData<T>.toSingleEvent(): LiveData<UiState<T>> {
    val result = SingleEventUi<UiState<T>>()
    result.addSource(this) {
        result.value = it
    }
    return result
}
