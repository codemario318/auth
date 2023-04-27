package com.mario.auth.dto

data class SignupRequestDto (
    val username: String,
    val email: String,
    val password: String
)