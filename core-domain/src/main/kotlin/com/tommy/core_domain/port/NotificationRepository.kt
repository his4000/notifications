package com.tommy.core_domain.port

import com.tommy.core_domain.model.Notification

interface NotificationRepository {
    fun save(notification: Notification): Notification
}
