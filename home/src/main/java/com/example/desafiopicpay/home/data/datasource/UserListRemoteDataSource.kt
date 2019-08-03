package com.example.desafiopicpay.home.data.datasource

import androidx.lifecycle.LiveData
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.home.data.UserListAPI
import com.example.desafiopicpay.network.ui.UiState

internal class UserListRemoteDataSource(
    private val userListAPI: UserListAPI
) : UserListDataSource {
    override fun getUserList(): LiveData<UiState<List<UserDTO>>> =
        userListAPI.getUserList()
}
