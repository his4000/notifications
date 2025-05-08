package com.tommy.server.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SmsService : NotificationService {
    private val log = LoggerFactory.getLogger(SmsService::class.java)

    override fun send(notification: Notification) {
        log.info("### send SMS to {}: {}", notification.recipient, notification.body)
        Thread.sleep(3000)
        log.info("### send SMS complete to {}", notification.recipient)
    }
}
