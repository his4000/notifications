package com.tommy.server.dto

import com.tommy.server.service.Notification

data class KakaotalkSendDto(
    val title: String,
    val body: String,
    val userId: String
) {
    fun toNotification(): Notification {
        return Notification(
            title = title,
            body = body,
            recipient = userId
        )
    }
}
