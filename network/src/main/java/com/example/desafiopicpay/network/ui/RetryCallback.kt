package com.example.desafiopicpay.network.ui

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * {@link Callback<T>} implementation with capability to do a retry
 */
abstract class RetryCallback<RESPONSE> : Callback<RESPONSE> {

    private lateinit var call: Call<RESPONSE>

    override fun onFailure(call: Call<RESPONSE>, t: Throwable) {
        this.call = call
    }

    override fun onResponse(call: Call<RESPONSE>, response: Response<RESPONSE>) {
        this.call = call
    }

    /**
     * Retry given call enqueing with this callback
     */
    abstract fun onRetry()

    fun retry() {
        call.clone().enqueue(this)
    }
}

class RetryCall<RESPONSE>(private val retryCallback: RetryCallback<RESPONSE>) {

    fun retry() {
        retryCallback.retry()
        retryCallback.onRetry()
    }
}
