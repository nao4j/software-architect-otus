package com.nao4j.otus.architect.apigateway.idently.service

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jwt.JWT

interface TokenService {
    fun generateJwt(payload: TokenPayload): JWT

    fun generateJwk(): JWKSet
}

data class TokenPayload(val login: String, val firstName: String, val lastName: String)
