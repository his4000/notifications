package com.tommy.core_adapater.repository.jpa

import com.tommy.core_adapater.repository.jpa.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findFirstByUserIdAndChannel(userId: String, channel: String): UserEntity?
}
