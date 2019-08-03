package com.example.desafiopicpay.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.home.data.UserListRepository
import com.example.desafiopicpay.network.ui.UiState
import com.example.desafiopicpay.network.ui.UiStateLiveData

internal class HomeViewModel(
    private val userListRepository: UserListRepository
): ViewModel() {

    fun loadUserList(): LiveData<UiState<List<UserDTO>>> {
        return userListRepository.getUserList()
    }

}