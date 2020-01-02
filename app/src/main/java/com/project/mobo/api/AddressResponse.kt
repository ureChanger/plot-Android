package com.project.mobo.api

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    val status: Int,
    val message: String,
    val data: Chatting
)

data class Chatting(
    @SerializedName("uid")
    val uid: String,
    @SerializedName("address")
    val address: String

)