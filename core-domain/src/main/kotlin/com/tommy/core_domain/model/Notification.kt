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
    val eventAt: ZonedDateTime,
    val createdAt: ZonedDateTime? = ZonedDateTime.now()
)
