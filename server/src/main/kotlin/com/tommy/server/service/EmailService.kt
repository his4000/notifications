package com.tommy.server.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmailService : NotificationService {
    private val log = LoggerFactory.getLogger(EmailService::class.java)

    override fun send(notification: Notification) {
        log.info("### send e-mail")
        Thread.sleep(3000)
        log.info("### send e-mail complete")
    }
}
