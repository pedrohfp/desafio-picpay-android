package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData
import retrofit2.http.GET

internal interface UserListAPI {
    @GET("/tests/mobdev/users")
    fun getUserList(): LiveData<UserDTO>
}