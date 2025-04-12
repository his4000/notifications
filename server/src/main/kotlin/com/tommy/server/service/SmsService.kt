package com.tommy.server.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SmsService : NotificationService {
    val log = LoggerFactory.getLogger(SmsService::class.java)

    override fun send(notification: Notification) {
        log.info("### send SMS")
        Thread.sleep(3000)
        log.info("### send SMS complete")
    }
}
