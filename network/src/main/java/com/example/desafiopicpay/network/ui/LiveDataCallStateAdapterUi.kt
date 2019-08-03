package com.example.desafiopicpay.network.ui

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class LiveDataCallStateAdapterUi<R : Any>(private val responseType: Type)
    : CallAdapter<R, LiveData<UiState<R>>> {

    override fun adapt(call: Call<R>): LiveData<UiState<R>> {
        return UiLiveData(call)
    }

    override fun responseType() = responseType
}
