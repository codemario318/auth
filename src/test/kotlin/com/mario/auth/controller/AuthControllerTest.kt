package com.mario.auth.controller

import com.mario.auth.domain.User
import com.mario.auth.dto.SignupRequestDto
import com.mario.auth.repository.UserRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest @Autowired constructor(
    val authController: AuthController,
    val testRestTemplate: TestRestTemplate,
    val userRepository: UserRepository,
) {
    @Test
    fun `회원 가입 성공`() {
        val email = "codemario@gmail.com"
        val password = "password"
        val name = "mario"

        val responseEntity = authController.signup(SignupRequestDto(email, password, name))

        assertEquals(HttpStatus.OK, responseEntity.statusCode)
    }

    @Test
    fun `회원 가입 실패 - 중복`() {
        val email = "codemario@gmail.com"
        val password = "password"
        val name = "mario"

        val user = User(
            null,
            email,
            password,
            name
        )

        userRepository.save(user)

        val responseEntity = authController.signup(SignupRequestDto(email, password, name))

        assertEquals(HttpStatus.CONFLICT, responseEntity.statusCode)
    }

    @Test
    fun `회원 가입 실패 - 이메일 누락`() {
        val email = ""
        val password = "password"
        val name = "mario"

        val responseEntity = authController.signup(SignupRequestDto(email, password, name))
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
    }

    @Test
    fun `회원 가입 실패 - 이메일 형식 오류`() {
        val email = "codemario318"
        val password = "password"
        val name = "mario"

        val responseEntity = authController.signup(SignupRequestDto(email, password, name))

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
    }

    @Test
    fun `회원 가입 실패 - 비밀번호 누락`() {
        val email = "codemario318"
        val password = ""
        val name = "mario"

        val responseEntity = authController.signup(SignupRequestDto(email, password, name))

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
    }

    @Test
    fun `회원 가입 실패 - 이름 누락`() {
        val email = "codemario318"
        val password = "password"
        val name = ""

        val responseEntity = authController.signup(SignupRequestDto(email, password, name))

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.statusCode)
    }
}