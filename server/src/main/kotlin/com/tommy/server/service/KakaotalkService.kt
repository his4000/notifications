package com.tommy.server.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class KakaotalkService : NotificationService {
    private val log = LoggerFactory.getLogger(KakaotalkService::class.java)

    override fun send(notification: Notification) {
        log.info("### send Kakaotalk to {}: {}", notification.recipient, notification.body)
        Thread.sleep(3000)
        log.info("### send Kakaotalk complete to {}", notification.recipient)
    }
}
