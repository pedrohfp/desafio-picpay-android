package com.example.desafiopicpay.home.data

import com.google.gson.annotations.SerializedName

internal class UserDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("username")
    val username: String
)
