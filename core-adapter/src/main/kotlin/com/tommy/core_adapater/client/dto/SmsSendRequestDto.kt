package com.tommy.core_adapater.client.dto

data class SmsSendRequestDto(
    val title: String,
    val body: String,
    val phoneNumber: String
)
