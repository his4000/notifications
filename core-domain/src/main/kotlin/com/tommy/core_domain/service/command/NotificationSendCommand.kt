package com.tommy.core_domain.service.command

import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.enums.SendType

data class NotificationSendCommand(
    val sendType: SendType,
    val channel: Channel,
    val userId: String,
    val title: String,
    val content: String,
    val recipient: String
)
