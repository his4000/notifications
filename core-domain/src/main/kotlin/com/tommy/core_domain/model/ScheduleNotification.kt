package com.tommy.core_domain.model

import com.tommy.core_domain.enums.SendType
import java.time.ZonedDateTime

data class ScheduleNotification(
    val notification: Notification,
    val scheduledAt: ZonedDateTime
) {
    val sendType = SendType.SCHEDULE
}
