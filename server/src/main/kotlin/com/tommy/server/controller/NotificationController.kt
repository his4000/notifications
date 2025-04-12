package com.tommy.server.controller

import com.tommy.server.dto.EmailSendDto
import com.tommy.server.dto.KakaotalkSendDto
import com.tommy.server.dto.SmsSendDto
import com.tommy.server.service.EmailService
import com.tommy.server.service.KakaotalkService
import com.tommy.server.service.SmsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val smsService: SmsService,
    private val emailService: EmailService,
    private val kakaotalkService: KakaotalkService
) {

    @PostMapping("/send/sms")
    fun sendSms(@RequestBody dto: SmsSendDto) {
        smsService.send(dto.toNotification())
    }

    @PostMapping("/send/email")
    fun sendEmail(@RequestBody dto: EmailSendDto) {
        emailService.send(dto.toNotification())
    }

    @PostMapping("/send/kakaotalk")
    fun sendKakaotalk(@RequestBody dto: KakaotalkSendDto) {
        kakaotalkService.send(dto.toNotification())
    }
}
