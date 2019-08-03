package com.example.desafiopicpay.home.utils

import com.example.desafiopicpay.home.data.UserDTO

internal val fakeUserList  = listOf(createFakeUserList())

private fun createFakeUserList() =
    UserDTO("0", "teste", "teste", "teste")