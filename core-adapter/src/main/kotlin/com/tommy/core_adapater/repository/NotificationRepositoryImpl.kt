package com.tommy.core_adapater.repository

import com.tommy.core_adapater.repository.jpa.NotificationJpaRepository
import com.tommy.core_adapater.repository.jpa.UserRepository
import com.tommy.core_adapater.repository.jpa.entities.NotificationEntity
import com.tommy.core_domain.enums.Channel
import com.tommy.core_domain.enums.SendStatus
import com.tommy.core_domain.enums.SendType
import com.tommy.core_domain.model.Notification
import com.tommy.core_domain.port.NotificationRepository
import com.tommy.core_domain.utils.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class NotificationRepositoryImpl(
    private val jpaRepository: NotificationJpaRepository,
    private val userRepository: UserRepository
) : NotificationRepository {

    override fun save(notification: Notification): Notification {
        return jpaRepository.save(notification.toEntity()).toModel()
    }

    override fun findByUserId(userId: String, pageSize: Int, pageNumber: Int): Page<Notification> {
        val page = jpaRepository.findByUserUserId(userId, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "notificationId")))
        return Page(
            totalPages = page.totalPages,
            currentPage = page.number,
            totalElements = page.totalElements,
            currentElements = page.content.size.toLong(),
            content = page.content.map { it.toModel() }
        )
    }

    override fun findByScheduledAt(scheduledAt: ZonedDateTime, pageSize: Int, pageNumber: Int): Page<Notification> {
        val page = jpaRepository.findByScheduledAt(scheduledAt, PageRequest.of(pageSize, pageNumber, Sort.by(Sort.Direction.ASC, "notificationId")))
        return Page(
            totalPages = page.totalPages,
            currentPage = page.number,
            totalElements = page.totalElements,
            currentElements = page.content.size.toLong(),
            content = page.content.map { it.toModel() }
        )
    }

    override fun findFailures(pageSize: Int, pageNumber: Int): Page<Notification> {
        val page = jpaRepository.findBySendStatus(SendStatus.FAILED.name, PageRequest.of(pageSize, pageNumber, Sort.by(Sort.Direction.ASC, "notificationId")))
        return Page(
            totalPages = page.totalPages,
            currentPage = page.number,
            totalElements = page.totalElements,
            currentElements = page.content.size.toLong(),
            content = page.content.map { it.toModel() }
        )
    }

    private fun Notification.toEntity(): NotificationEntity {
        val user = userRepository.findFirstByUserIdAndChannel(this.userId, this.channel.name)
            ?: throw IllegalStateException("Cannot find user info: userId=${this.userId} channel=${this.channel.name}")
        return NotificationEntity(
            notificationId = this.notificationId,
            sendType = this.sendType.name,
            user = user,
            title = this.title,
            content = this.content,
            sendStatus = this.sendStatus.name,
            failureReason = this.failureReason,
            sentAt = this.sentAt,
            scheduledAt = this.scheduledAt
        )
    }

    private fun NotificationEntity.toModel(): Notification {
        return Notification(
            notificationId = this.notificationId,
            sendType = SendType.valueOf(this.sendType),
            channel = Channel.valueOf(this.user.channel),
            userId = this.user.userId,
            title = this.title,
            content = this.content,
            recipient = this.user.recipient,
            sendStatus = SendStatus.valueOf(this.sendStatus),
            failureReason = this.failureReason,
            sentAt = this.sentAt,
            scheduledAt = this.scheduledAt
        )
    }
}
