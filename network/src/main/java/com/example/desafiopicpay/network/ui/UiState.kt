package com.example.desafiopicpay.network.ui

sealed class UiState<out T>

object UiLoading : UiState<Nothing>()

class UiSuccess<T>(val data: T) : UiState<T>()

class UiError(val errorData: ErrorData) : UiState<Nothing>()
