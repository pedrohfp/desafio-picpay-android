package com.example.desafiopicpay.network.ui

class ErrorData(
    val throwable: Throwable,
    val code: Int? = null,
    val errorBody: String? = null
)
