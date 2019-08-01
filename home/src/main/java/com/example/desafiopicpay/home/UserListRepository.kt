package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData

internal class UserListRepository(
    private val userListRemoteDataSource: UserListRemoteDataSource
) : UserListDataSource {
    override fun getUserList(): LiveData<List<UserDTO>> =
        userListRemoteDataSource.getUserList()
}
