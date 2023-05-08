package com.mario.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["com.mario.auth.service"])

class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}
