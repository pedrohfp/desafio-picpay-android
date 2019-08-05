package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mockito.stubbing.OngoingStubbing

/**
 * Stub a Livedata and post it value when called
 */
fun <T> OngoingStubbing<out LiveData<T>>.mockAndSendResponse(
    value: T,
    liveData: LiveData<T> = MutableLiveData()
) {
    thenAnswer { _ ->
        liveData
            .also {
                (it as? MutableLiveData)?.postValue(value)
            }
    }
}
