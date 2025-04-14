package com.tommy.core_adapater.kafka.dto

data class NotificationSendMessage(
    val sendType: String,
    val channel: String,
    val userId: String,
    val title: String,
    val content: String,
    val recipient: String,
    val scheduledAt: String? = null  // yyyyMMddHHmm
)
