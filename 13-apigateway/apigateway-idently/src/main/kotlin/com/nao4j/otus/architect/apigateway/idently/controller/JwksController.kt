package com.nao4j.otus.architect.apigateway.idently.controller

import com.nao4j.otus.architect.apigateway.idently.service.TokenService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JwksController(private val tokenService: TokenService) {
    @GetMapping("/.well-known/jwks.json")
    fun getJwks() = tokenService.generateJwk().toJSONObject()
}
