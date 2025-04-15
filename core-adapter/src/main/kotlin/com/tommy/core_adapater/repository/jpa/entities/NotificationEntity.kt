package com.tommy.core_adapater.repository.jpa.entities

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "notifications")
class NotificationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var notificationId: Long? = null,

    @Column
    var sendType: String,

    @ManyToOne
    @JoinColumn(name = "userSrl")
    var user: UserEntity,

    @Column
    var title: String,

    @Column
    var content: String,

    @Column
    var sendStatus: String,

    @Column
    var failureReason: String? = null,

    @Column
    var sentAt: ZonedDateTime? = null,

    @Column
    var scheduledAt: ZonedDateTime? = null,
) : BaseEntity()
