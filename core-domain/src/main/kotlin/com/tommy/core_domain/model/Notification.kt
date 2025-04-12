package com.tommy.core_domain.model

import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.enums.SendStatus
import com.tommy.core_domain.enums.SendType
import java.time.ZonedDateTime

data class Notification(
    val notificationId: Long? = null,
    val sendType: SendType,
    val channel: Channel,
    val userId: String,
    val title: String,
    val content: String,
    val recipient: String,
    val sendStatus: SendStatus = SendStatus.PENDING,
    val failureReason: String? = null,
    val sentAt: ZonedDateTime? = null,
    val scheduledAt: ZonedDateTime? = null,
    val createdAt: ZonedDateTime? = ZonedDateTime.now()
) {
    fun fail(reason: String): Notification {
        check(SendStatus.SENT != sendStatus) {
            "fail: status must not be SENT: notificationId=$notificationId"
        }
        return this.copy(sendStatus = SendStatus.FAILED, failureReason = reason)
    }

    fun send(): Notification {
        return this.copy(sendStatus = SendStatus.SENT, sentAt = ZonedDateTime.now(), failureReason = null)
    }

    fun schedule(scheduledAt: ZonedDateTime): Notification {
        check(SendStatus.PENDING == sendStatus) {
            "schedule: status must be PENDING: notificationId=$notificationId"
        }
        check(SendType.SCHEDULE == sendType) {
            "schedule: type must be SCHEDULE: notificationId=$notificationId"
        }
        return this.copy(sendStatus = SendStatus.SCHEDULED, scheduledAt = scheduledAt, failureReason = null)
    }
}
