package com.tommy.core_adapater.sender

import com.tommy.core_adapater.client.ServerApiClient
import com.tommy.core_adapater.client.dto.EmailSendRequestDto
import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.model.Notification
import com.tommy.core_domain.port.NotificationSender
import org.springframework.stereotype.Service

@Service
class EmailSender(
    private val apiClient: ServerApiClient
) : NotificationSender {
    override fun getChannel() = Channel.EMAIL

    override fun send(notification: Notification) {
        val response = apiClient.sendEmail(
            EmailSendRequestDto(
                title = notification.title,
                body = notification.content,
                emailAddress = notification.recipient
            )
        )

        if (!response.isSuccess()) {
            throw IllegalStateException("server API call failed (Email): notificationId=${notification.notificationId} code=${response.code} message=${response.message}")
        }
    }
}
