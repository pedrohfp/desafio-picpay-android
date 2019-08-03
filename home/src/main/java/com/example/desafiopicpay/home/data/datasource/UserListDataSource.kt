package com.example.desafiopicpay.home.data.datasource

import androidx.lifecycle.LiveData
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.network.ui.UiState

internal interface UserListDataSource {
    fun getUserList(): LiveData<UiState<List<UserDTO>>>
}
