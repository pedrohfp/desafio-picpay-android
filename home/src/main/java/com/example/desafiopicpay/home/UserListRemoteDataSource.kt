package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData
import com.example.desafiopicpay.network.ui.UiState

internal class UserListRemoteDataSource(
    private val userListAPI: UserListAPI
) : UserListDataSource {
    override fun getUserList(): LiveData<UiState<List<UserDTO>>> =
        userListAPI.getUserList()
}
