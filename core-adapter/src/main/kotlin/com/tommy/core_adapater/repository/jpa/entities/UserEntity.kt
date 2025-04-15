package com.tommy.core_adapater.repository.jpa.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userSrl: Long? = null,

    @Column
    var userId: String,

    @Column
    var channel: String,

    @Column
    var recipient: String
) : BaseEntity()
