package com.tommy.server.dto

import com.tommy.server.service.Notification

data class EmailSendDto(
    val title: String,
    val body: String,
    val emailAddress: String
) {
    fun toNotification(): Notification {
        return Notification(
            title = title,
            body = body,
            recipient = emailAddress
        )
    }
}
