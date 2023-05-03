package com.mario.auth.controller

import com.mario.auth.dto.SignupRequestDto
import com.mario.auth.exception.UserAlreadyExistsException
import com.mario.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signupRequest: SignupRequestDto): ResponseEntity<Any> {
        return try {
            authService.signup(signupRequest)
            ResponseEntity.ok().build()
        } catch (e: UserAlreadyExistsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }
}