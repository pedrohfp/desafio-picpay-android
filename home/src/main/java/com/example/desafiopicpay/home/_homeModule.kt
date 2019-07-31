package com.example.desafiopicpay.home

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