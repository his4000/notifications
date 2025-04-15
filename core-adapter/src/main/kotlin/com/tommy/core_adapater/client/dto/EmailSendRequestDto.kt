package com.tommy.core_adapater.client.dto

data class EmailSendRequestDto(
    val title: String,
    val body: String,
    val emailAddress: String
)
