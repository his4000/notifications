package com.tommy.server.controller

import com.tommy.server.dto.EmailSendDto
import com.tommy.server.dto.KakaotalkSendDto
import com.tommy.server.dto.ResponseDto
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
    fun sendSms(@RequestBody dto: SmsSendDto): ResponseDto {
        smsService.send(dto.toNotification())
        return ResponseDto.success()
    }

    @PostMapping("/send/email")
    fun sendEmail(@RequestBody dto: EmailSendDto): ResponseDto {
        emailService.send(dto.toNotification())
        return ResponseDto.success()
    }

    @PostMapping("/send/kakaotalk")
    fun sendKakaotalk(@RequestBody dto: KakaotalkSendDto): ResponseDto {
        kakaotalkService.send(dto.toNotification())
        return ResponseDto.success()
    }
}
