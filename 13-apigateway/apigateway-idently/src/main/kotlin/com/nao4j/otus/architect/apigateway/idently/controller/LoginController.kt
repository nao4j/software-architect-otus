package com.nao4j.otus.architect.apigateway.idently.controller

import com.nao4j.otus.architect.apigateway.idently.repostory.AuthUserRepository
import com.nao4j.otus.architect.apigateway.idently.service.TokenPayload
import com.nao4j.otus.architect.apigateway.idently.service.TokenService
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(private val authUserRepository: AuthUserRepository, private val tokenService: TokenService) {
    @PostMapping("/login")
    fun login(@RequestBody data: LoginData): ResponseEntity<Nothing> {
        val jwt = authUserRepository.findByLoginAndPassword(data.login, data.password)?.let {
            tokenService.generateJwt(
                TokenPayload(
                    login = it.login,
                    firstName = it.firstName,
                    lastName = it.lastName
                )
            )
        }
        return if (jwt != null) {
            ok().header("X-ID-Token", jwt.serialize()).build()
        } else {
            status(UNAUTHORIZED).build()
        }
    }
}

data class LoginData(val login: String, val password: String)
