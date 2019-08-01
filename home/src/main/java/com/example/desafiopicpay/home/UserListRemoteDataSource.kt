package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData

internal class UserListRemoteDataSource(
    private val userListAPI: UserListAPI
) : UserListDataSource {
    override fun getUserList(): LiveData<UserDTO> =
        userListAPI.getUserList()
}
