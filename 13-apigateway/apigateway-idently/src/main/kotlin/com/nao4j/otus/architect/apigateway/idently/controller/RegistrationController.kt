package com.nao4j.otus.architect.apigateway.idently.controller

import com.nao4j.otus.architect.apigateway.idently.model.AuthUser
import com.nao4j.otus.architect.apigateway.idently.repostory.AuthUserRepository
import org.springframework.http.HttpStatus.CREATED
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController(private val authUserRepository: AuthUserRepository) {
    @Transactional
    @ResponseStatus(CREATED)
    @PostMapping("/register")
    fun register(@RequestBody data: RegistrationData): CreatedUser {
        val identity = authUserRepository.save(
            AuthUser(
                login = data.login,
                password = data.password,
                firstName = data.firstName,
                lastName = data.lastName,
            )
        )
        return CreatedUser(identity.id)
    }
}

data class RegistrationData(val login: String, val password: String, val firstName: String, val lastName: String)

data class CreatedUser(val id: Long)
