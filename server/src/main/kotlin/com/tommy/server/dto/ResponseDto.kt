package com.tommy.server.dto

data class ResponseDto(
    val code: String,
    val message: String
) {
    companion object {
        fun success(): ResponseDto {
            return ResponseDto(
                code = "SUCCESS",
                message = "Success"
            )
        }
    }
}
