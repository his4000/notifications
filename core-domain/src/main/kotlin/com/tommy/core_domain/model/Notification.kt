package com.tommy.core_domain.model

import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.enums.SendStatus
import java.time.ZonedDateTime

data class Notification(
    val notificationId: Long? = null,
    val channel: Channel,
    val title: String,
    val content: String,
    val recipient: String,
    val sendStatus: SendStatus,
    val failureReason: String? = null,
    val eventAt: ZonedDateTime,
    val createdAt: ZonedDateTime? = ZonedDateTime.now()
) {
    fun fail(reason: String): Notification {
        check(SendStatus.SENT != sendStatus) {
            "fail: status must not be SENT: $notificationId"
        }
        return this.copy(sendStatus = SendStatus.FAILED, failureReason = reason)
    }

    fun send(): Notification {
        return this.copy(sendStatus = SendStatus.SENT, failureReason = null)
    }

    fun schedule(): Notification {
        check(SendStatus.PENDING == sendStatus) {
            "schedule: status must be PENDING: $notificationId"
        }
        return this.copy(sendStatus = SendStatus.SCHEDULED)
    }
}
