package com.tommy.core_adapater.kafka.consumer

import com.tommy.core_adapater.kafka.dto.NotificationSendMessage
import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.enums.SendType
import com.tommy.core_domain.service.NotificationService
import com.tommy.core_domain.service.command.NotificationScheduleCommand
import com.tommy.core_domain.service.command.NotificationSendCommand
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Service
class NotificationSendConsumer(
    private val notificationService: NotificationService
) {

    @KafkaListener(topics = ["notification-send.1"], groupId = "notification-send-consumer.1")
    fun consume(message: NotificationSendMessage) {
        when (message.sendType) {
            SendType.IMMEDIATE.name -> {
                require(message.scheduledAt == null) { "scheduledAt must be null at immediate sending" }
                notificationService.send(
                    NotificationSendCommand(
                        sendType = SendType.valueOf(message.sendType),
                        channel = Channel.valueOf(message.channel),
                        userId = message.userId,
                        title = message.title,
                        content = message.content,
                        recipient = message.recipient
                    )
                )
            }
            SendType.SCHEDULE.name -> {
                requireNotNull(message.scheduledAt) { "scheduledAt must not be null at scheduled sending" }
                notificationService.schedule(
                    NotificationScheduleCommand(
                        sendType = SendType.valueOf(message.sendType),
                        channel = Channel.valueOf(message.channel),
                        userId = message.userId,
                        title = message.title,
                        content = message.content,
                        recipient = message.recipient,
                        scheduledAt = ZonedDateTime.parse(message.scheduledAt, DateTimeFormatter.ofPattern("yyyyMMddHHmm"))
                    )
                )
            }
            else -> throw IllegalArgumentException("Unexpected message type ${message.sendType}")
        }
    }
}
