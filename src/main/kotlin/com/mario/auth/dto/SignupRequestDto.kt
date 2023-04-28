package com.mario.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignupRequestDto (
    @field:NotBlank
    @Email
    val email: String,
    @field:NotBlank
    @field:Size(min = 10, max = 50)
    val password: String,
    @field:NotBlank
    @field:Size(min = 2, max = 10)
    val name: String,
)