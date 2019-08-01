package com.example.desafiopicpay.home.di

import com.example.desafiopicpay.home.UserListAPI
import com.example.desafiopicpay.home.UserListDataSource
import com.example.desafiopicpay.home.UserListRemoteDataSource
import com.example.desafiopicpay.home.UserListRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    factory<UserListDataSource> {
        val retrofit: Retrofit = get()
        UserListRemoteDataSource(retrofit.create(UserListAPI::class.java))
    }

    factory {
        UserListRepository(get())
    }
}
