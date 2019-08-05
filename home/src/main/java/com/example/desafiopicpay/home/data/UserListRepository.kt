package com.example.desafiopicpay.home.data

import androidx.lifecycle.LiveData
import com.example.desafiopicpay.home.data.datasource.UserListDataSource
import com.example.desafiopicpay.home.data.datasource.UserListRemoteDataSource
import com.example.desafiopicpay.network.ui.UiState

internal class UserListRepository(
    private val userListRemoteDataSource: UserListRemoteDataSource
) : UserListDataSource {
    override fun getUserList(): LiveData<UiState<List<UserDTO>>> =
        userListRemoteDataSource.getUserList()
}
