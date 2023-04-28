package com.mario.auth.repository

import com.mario.auth.domain.User
import com.mario.auth.exception.UserAlreadyExistsException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
) {
    @Test
    fun `이메일로 회원 찾기`() {
        val user = User(
            null,
            "codemario318@gmail.com",
            "codemario318",
            "mario"
        )

        entityManager.persist(user)
        entityManager.flush()

        val findUser = userRepository.findByEmail(user.email)

        assertEquals(findUser, user)
    }

    @Test
    fun `중복 이메일 확인하기`() {
        val email = "codemario318@gmail.com"
        val eamil2 = "codemario319@gmail.com"

        val user = User(
            null,
            email,
            "codemario318",
            "mario"
        )

        entityManager.persist(user)
        entityManager.flush()

        val existEmailResult = userRepository.existsByEmail(email)
        val notExistEmailResult = userRepository.existsByEmail(eamil2)

        assertEquals(existEmailResult, true)
        assertEquals(notExistEmailResult, false)
    }
}