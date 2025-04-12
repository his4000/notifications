package com.tommy.core_domain.port

import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.model.Notification

interface NotificationSender {
    fun getChannel(): Channel
    fun send(notification: Notification)
}
