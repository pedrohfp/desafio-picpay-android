package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData
import com.example.desafiopicpay.network.ui.UiState

internal class UserListRepository(
    private val userListRemoteDataSource: UserListRemoteDataSource
) : UserListDataSource {
    override fun getUserList(): LiveData<UiState<List<UserDTO>>> =
        userListRemoteDataSource.getUserList()
}
