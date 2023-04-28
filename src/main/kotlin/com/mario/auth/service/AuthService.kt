package com.mario.auth.service

import com.mario.auth.dto.SignupRequestDto
import com.mario.auth.domain.User
import com.mario.auth.exception.UserAlreadyExistsException
import com.mario.auth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(signupRequest: SignupRequestDto) {
        if (userRepository.existsByEmail(signupRequest.email)) {
            throw UserAlreadyExistsException()
        }

        val user = User(
            email = signupRequest.email,
            password = passwordEncoder.encode(signupRequest.password),
            name = signupRequest.name,
        )

        userRepository.save(user)
    }
}