package com.tommy.core_domain.port

import com.tommy.core_domain.model.Notification
import com.tommy.core_domain.utils.Page
import java.time.ZonedDateTime

interface NotificationRepository {
    fun save(notification: Notification): Notification
    fun findByUserId(userId: String, pageSize: Int, pageNumber: Int): Page<Notification>
    fun findByScheduledAt(scheduledAt: ZonedDateTime, pageSize: Int, pageNumber: Int): Page<Notification>
    fun findFailures(pageSize: Int, pageNumber: Int): Page<Notification>
}
