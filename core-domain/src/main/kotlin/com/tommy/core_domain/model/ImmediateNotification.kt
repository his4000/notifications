package com.tommy.core_domain.model

import com.tommy.core_domain.enums.SendStatus
import com.tommy.core_domain.enums.SendType

data class ImmediateNotification(
    val notification: Notification
) {
    val sendType = SendType.IMMEDIATE

    init {
        check(notification.sendStatus != SendStatus.SCHEDULED) {
            "Immediate notification status must not be ${SendStatus.SCHEDULED}: notificationId=${notification.notificationId}"
        }
    }
}
