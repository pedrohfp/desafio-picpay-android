package com.example.desafiopicpay.home.di

import com.example.desafiopicpay.home.HomeViewModel
import com.example.desafiopicpay.home.data.UserListAPI
import com.example.desafiopicpay.home.data.UserListRepository
import com.example.desafiopicpay.home.data.datasource.UserListRemoteDataSource
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    factory {
        val retrofit: Retrofit = get()
        UserListRemoteDataSource(
            retrofit.create(
                UserListAPI::class.java
            )
        )
    }

    factory {
        UserListRepository(get())
    }

    factory {
        HomeViewModel(get())
    }
}
