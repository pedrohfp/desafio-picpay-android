package com.example.desafiopicpay.network

import com.example.desafiopicpay.network.adapter.LiveDataCallAdapterFactory
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(BuildConfig.API_URL)
            .client(get())
            .build()
    }


}