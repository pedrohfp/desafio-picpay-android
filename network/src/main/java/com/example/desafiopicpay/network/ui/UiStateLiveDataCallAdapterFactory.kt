package com.example.desafiopicpay.network.ui

import androidx.lifecycle.LiveData
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.CallAdapter
import retrofit2.Retrofit

internal class UiStateLiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return createCallAdapter(returnType)
    }

    private fun createCallAdapter(returnType: Type) =
        if (isNotLiveData(returnType))
            null
        else
            createCallAdapterInstance(returnType)

    private fun isNotLiveData(returnType: Type) =
        CallAdapter.Factory.getRawType(returnType) != LiveData::class.java

    private fun createCallAdapterInstance(returnType: Type): LiveDataCallStateAdapterUi<Any>? {
        val observableType = getObservableType(returnType)
            ?: throw IllegalArgumentException("resource must be parameterized")

        return if (!isUiState(observableType))
            null
        else createCallAdapterInstance(observableType)
    }

    private fun createCallAdapterInstance(observableType: ParameterizedType): LiveDataCallStateAdapterUi<Any> {
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)

        return LiveDataCallStateAdapterUi(bodyType)
    }

    private fun getObservableType(returnType: Type) =
        CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
            as? ParameterizedType

    private fun isUiState(returnType: ParameterizedType?): Boolean {
        return returnType?.rawType == UiState::class.java
    }
}
