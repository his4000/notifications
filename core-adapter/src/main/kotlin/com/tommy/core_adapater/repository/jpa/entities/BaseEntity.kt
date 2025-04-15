package com.tommy.core_adapater.repository.jpa.entities

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime

@MappedSuperclass
abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    var createdAt: ZonedDateTime? = null

    @LastModifiedDate
    var modifiedAt: ZonedDateTime? = null
}
