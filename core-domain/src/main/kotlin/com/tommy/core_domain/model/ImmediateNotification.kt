package com.tommy.core_domain.model

import com.tommy.core_domain.enums.SendType

data class ImmediateNotification(
    val notification: Notification
) {
    val sendType = SendType.IMMEDIATE
}
