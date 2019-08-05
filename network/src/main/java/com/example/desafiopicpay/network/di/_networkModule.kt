package com.example.desafiopicpay.network.di

import androidx.annotation.VisibleForTesting
import com.example.desafiopicpay.network.BuildConfig
import com.example.desafiopicpay.network.LoggingInterceptor
import com.example.desafiopicpay.network.ui.UiStateLiveDataCallAdapterFactory
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@VisibleForTesting
var url: String? = null

val networkModule = module {

    factory<Interceptor> {
        LoggingInterceptor().getInterceptor()
    }

    factory {
        val interceptor: Interceptor = get()

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(UiStateLiveDataCallAdapterFactory())
            .baseUrl(url ?: BuildConfig.API_URL)
            .client(get())
            .build()
    }
}
