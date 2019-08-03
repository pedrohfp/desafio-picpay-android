package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData
import com.example.desafiopicpay.network.ui.UiState

internal interface UserListDataSource {
    fun getUserList(): LiveData<UiState<List<UserDTO>>>
}
