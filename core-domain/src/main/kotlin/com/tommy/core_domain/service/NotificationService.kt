package com.tommy.core_domain.service

import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.model.Notification
import com.tommy.core_domain.port.NotificationRepository
import com.tommy.core_domain.port.NotificationSender
import com.tommy.core_domain.service.command.NotificationScheduleCommand
import com.tommy.core_domain.service.command.NotificationSendCommand
import com.tommy.core_domain.utils.Page
import org.slf4j.LoggerFactory
import java.time.ZonedDateTime

class NotificationService(
    private val repository: NotificationRepository,
    senders: List<NotificationSender>
) {
    private val senderMap: Map<Channel, NotificationSender> = senders.associateBy { it.getChannel() }
    private val log = LoggerFactory.getLogger(NotificationService::class.java)

    fun send(command: NotificationSendCommand) {
        log.info("send notification: channel=${command.channel}, title=${command.title}, content=${command.content}")

        // save pending
        val notification = repository.save(
            Notification(
                sendType = command.sendType,
                channel = command.channel,
                userId = command.userId,
                title = command.title,
                content = command.content,
                recipient = command.recipient
            )
        )

        kotlin.runCatching {
            senderMap[notification.channel]?.send(notification)
                ?: throw IllegalArgumentException("Invalid channel: ${notification.channel}")
        }.onSuccess {
            log.info("send notification success: ${notification.notificationId}")
            repository.save(notification.send())
        }.onFailure { e ->
            log.error("send notification fail: ${notification.notificationId}")
            repository.save(notification.fail(e.message ?: "Unknown error"))
        }
        log.info("send notification complete: ${notification.notificationId}")
    }

    fun schedule(command: NotificationScheduleCommand) {
        log.info("schedule notification: channel=${command.channel}, title=${command.title}, content=${command.content}")

        repository.save(
            Notification(
                sendType = command.sendType,
                channel = command.channel,
                userId = command.userId,
                title = command.title,
                content = command.content,
                recipient = command.recipient,
            ).schedule(command.scheduledAt)
        )
    }

    fun getScheduleSendTargets(scheduledAt: ZonedDateTime, pageSize: Int, pageNumber: Int): Page<Notification> {
        return repository.findByScheduledAt(scheduledAt, pageSize, pageNumber)
    }

    fun getFailResendTargets(pageSize: Int, pageNumber: Int): Page<Notification> {
        return repository.findFailures(pageSize, pageNumber)
    }

    fun getNotifications(userId: String, pageSize: Int, pageNumber: Int): Page<Notification> {
        return repository.findByUserId(userId, pageSize, pageNumber)
    }
}
