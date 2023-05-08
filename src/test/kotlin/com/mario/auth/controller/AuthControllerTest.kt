package com.mario.auth.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mario.auth.dto.SignupRequestDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(AuthController::class)
@AutoConfigureMockMvc
class AuthControllerTest{
    @Autowired
    private lateinit var mockMvc: MockMvc

    private val uri = "/api/auth/signup"

    @Test
    fun `회원 가입 성공`() {
        val email = "codemario@gmail.com"
        val password = "password"
        val name = "mario"

        val request = SignupRequestDto(email, password, name)
        val json = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

//    @Test
//    fun `회원 가입 실패 - 중복`() {
//        val email = "codemario@gmail.com"
//        val password = "password"
//        val name = "mario"
//
//        val user = User(
//            null,
//            email,
//            password,
//            name
//        )
//
//        val request = SignupRequestDto(email, password, name)
//        val json = jacksonObjectMapper().writeValueAsString(request)
//
//        mockMvc.perform(
//            MockMvcRequestBuilders.post(this.uri)
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isConflict)
//            .andDo(MockMvcResultHandlers.print())
//    }

    @Test
    fun `회원 가입 실패 - 이메일 누락`() {
        val email = ""
        val password = "password"
        val name = "mario"

        val request = SignupRequestDto(email, password, name)
        val json = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `회원 가입 실패 - 이메일 형식 오류`() {
        val email = "codemario318"
        val password = "password"
        val name = "mario"

        val request = SignupRequestDto(email, password, name)
        val json = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `회원 가입 실패 - 비밀번호 누락`() {
        val email = "codemario318"
        val password = ""
        val name = "mario"

        val request = SignupRequestDto(email, password, name)
        val json = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `회원 가입 실패 - 이름 누락`() {
        val email = "codemario318"
        val password = "password"
        val name = ""

        val request = SignupRequestDto(email, password, name)
        val json = jacksonObjectMapper().writeValueAsString(request)

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }
}