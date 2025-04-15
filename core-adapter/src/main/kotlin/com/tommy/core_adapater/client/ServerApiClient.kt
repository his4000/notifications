package com.tommy.core_adapater.client

import com.tommy.core_adapater.client.dto.EmailSendRequestDto
import com.tommy.core_adapater.client.dto.KakaotalkSendRequestDto
import com.tommy.core_adapater.client.dto.SendResponseDto
import com.tommy.core_adapater.client.dto.SmsSendRequestDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "serverApiClient",
    url = "\${notification.server.url}",
    configuration = [ServerApiClientConfiguration::class]
)
interface ServerApiClient {
    @PostMapping("/notification/send/sms")
    fun sendSms(@RequestBody requestDto: SmsSendRequestDto): SendResponseDto

    @PostMapping("/notification/send/email")
    fun sendEmail(@RequestBody requestDto: EmailSendRequestDto): SendResponseDto

    @PostMapping("/notification/send/kakaotalk")
    fun sendKakaotalk(@RequestBody requestDto: KakaotalkSendRequestDto): SendResponseDto
}
