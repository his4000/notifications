package com.tommy.server.dto

import com.tommy.server.service.Notification

data class SmsSendDto(
    val title: String,
    val body: String,
    val phoneNumber: String
) {
    fun toNotification(): Notification {
        return Notification(
            title = title,
            body = body,
            recipient = phoneNumber
        )
    }
}
