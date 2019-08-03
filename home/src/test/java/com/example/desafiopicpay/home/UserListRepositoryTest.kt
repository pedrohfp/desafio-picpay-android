package com.example.desafiopicpay.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.desafiopicpay.network.ui.UiState
import com.example.desafiopicpay.network.ui.UiSuccess
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

        val userListDataSource = UserListRemoteDataSource(userListAPI)

        userListRepository = UserListRepository(userListDataSource)
    }

    @Test
    fun whenGetUserListCalled_verifyObserverChanged() {
        val mockUserList = listOf(mockUserList())

        whenever(userListAPI.getUserList())
            .mockAndSendResponse(UiSuccess(mockUserList))

        val observer = mock<Observer<UiState<List<UserDTO>>>>()

        userListRepository.getUserList()
            .observeForever(observer)

        verify(observer).onChanged(UiSuccess(mockUserList))
    }

    private fun mockUserList() = UserDTO("0", "teste", "teste", "teste")
}
