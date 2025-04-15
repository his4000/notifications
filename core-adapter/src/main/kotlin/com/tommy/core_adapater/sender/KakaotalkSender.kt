package com.tommy.core_adapater.sender

import com.tommy.core_adapater.client.ServerApiClient
import com.tommy.core_adapater.client.dto.KakaotalkSendRequestDto
import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.model.Notification
import com.tommy.core_domain.port.NotificationSender
import org.springframework.stereotype.Service

@Service
class KakaotalkSender(
    private val apiClient: ServerApiClient
) : NotificationSender {
    override fun getChannel() = Channel.KAKAOTALK

    override fun send(notification: Notification) {
        val response = apiClient.sendKakaotalk(
            KakaotalkSendRequestDto(
                title = notification.title,
                body = notification.content,
                userId = notification.recipient
            )
        )

        if (!response.isSuccess()) {
            throw IllegalStateException("server API call failed (Kakaotalk): notificationId=${notification.notificationId} code=${response.code} message=${response.message}")
        }
    }
}
