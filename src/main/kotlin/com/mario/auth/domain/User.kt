package com.mario.auth.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val createdDate: Date = Date(),

    @Column(nullable = false)
    val updatedDate: Date = Date()
)