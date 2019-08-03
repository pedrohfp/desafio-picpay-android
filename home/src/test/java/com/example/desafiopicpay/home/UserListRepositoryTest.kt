package com.example.desafiopicpay.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.desafiopicpay.home.data.UserDTO
import com.example.desafiopicpay.home.data.UserListAPI
import com.example.desafiopicpay.home.data.datasource.UserListRemoteDataSource
import com.example.desafiopicpay.home.data.UserListRepository
import com.example.desafiopicpay.home.utils.fakeUserList
import com.example.desafiopicpay.home.utils.mockAndSendResponse
import com.example.desafiopicpay.network.ui.UiState
import com.example.desafiopicpay.network.ui.UiSuccess
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserListRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userListAPI: UserListAPI
    private lateinit var userListRepository: UserListRepository

    @Before
    fun setup() {
        userListAPI = mock()

        val userListDataSource =
            UserListRemoteDataSource(userListAPI)

        userListRepository =
            UserListRepository(userListDataSource)
    }

    @Test
    fun whenGetUserListCalled_verifyObserverChanged() {
        whenever(userListAPI.getUserList())
            .mockAndSendResponse(UiSuccess(fakeUserList))

        val observer = mock<Observer<UiState<List<UserDTO>>>>()

        userListRepository.getUserList()
            .observeForever(observer)

        verify(observer).onChanged(any())
    }
}
