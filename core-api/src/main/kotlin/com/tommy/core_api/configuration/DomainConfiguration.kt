package com.tommy.core_api.configuration

import com.tommy.core_domain.port.NotificationRepository
import com.tommy.core_domain.port.NotificationSender
import com.tommy.core_domain.service.NotificationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Bean
    fun notificationService(repository: NotificationRepository, senders: List<NotificationSender>): NotificationService {
        return NotificationService(repository, senders)
    }
}
