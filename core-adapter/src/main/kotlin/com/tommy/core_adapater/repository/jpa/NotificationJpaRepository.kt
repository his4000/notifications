package com.tommy.core_adapater.repository.jpa

import com.tommy.core_adapater.repository.jpa.entities.NotificationEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface NotificationJpaRepository : JpaRepository<NotificationEntity, Long> {
    fun findByUserUserId(userId: String, pageable: Pageable): Page<NotificationEntity>
    fun findByScheduledAt(scheduledAt: ZonedDateTime, pageable: Pageable): Page<NotificationEntity>
    fun findBySendStatus(sendStatus: String, pageable: Pageable): Page<NotificationEntity>
}
