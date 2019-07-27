package com.example.desafiopicpay.network

import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor {
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }
}