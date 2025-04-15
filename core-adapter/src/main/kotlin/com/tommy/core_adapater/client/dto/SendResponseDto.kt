package com.tommy.core_adapater.client.dto

data class SendResponseDto(
    val code: String,
    val message: String
) {
    fun isSuccess() = "SUCCESS" == code
}
