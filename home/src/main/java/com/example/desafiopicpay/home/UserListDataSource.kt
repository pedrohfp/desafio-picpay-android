package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData

internal interface UserListDataSource {
    fun getUserList(): LiveData<List<UserDTO>>
}
