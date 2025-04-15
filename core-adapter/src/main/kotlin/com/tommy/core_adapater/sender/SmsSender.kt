package com.tommy.core_adapater.sender

import com.tommy.core_adapater.client.ServerApiClient
import com.tommy.core_adapater.client.dto.SmsSendRequestDto
import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.model.Notification
import com.tommy.core_domain.port.NotificationSender
import org.springframework.stereotype.Service

@Service
class SmsSender(
    private val apiClient: ServerApiClient
) : NotificationSender {
    override fun getChannel() = Channel.SMS

    override fun send(notification: Notification) {
        val response = apiClient.sendSms(
            SmsSendRequestDto(
                title = notification.title,
                body = notification.content,
                phoneNumber = notification.recipient
            )
        )

        if (!response.isSuccess()) {
            throw IllegalStateException("server API call failed (SMS): notificationId=${notification.notificationId} code=${response.code} message=${response.message}")
        }
    }
}
