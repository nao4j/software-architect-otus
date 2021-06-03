package com.nao4j.otus.architect.apigateway.idently.service

import com.nimbusds.jose.JWSAlgorithm.RS256
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.RSASSASigner
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator
import com.nimbusds.jwt.JWT
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.stereotype.Service
import java.time.ZonedDateTime.now
import java.util.Date

@Service
class TokenServiceImpl : TokenService {
    private val privateKey = RSAKeyGenerator(2048).keyID("arch.homework").generate()

    private val jwk = privateKey.toPublicJWK()

    private val signer = RSASSASigner(privateKey)

    override fun generateJwt(payload: TokenPayload): JWT {
        val claimsSet = JWTClaimsSet.Builder()
            .issuer("http://arch.homework")
            .expirationTime(Date.from(now().plusMinutes(3).toInstant()))
            .subject(payload.login)
            .claim("first_name", payload.firstName)
            .claim("last_name", payload.lastName)
            .build()
        val jwt = SignedJWT(JWSHeader.Builder(RS256).keyID(privateKey.keyID).build(), claimsSet)
        jwt.sign(signer)
        return jwt
    }

    override fun generateJwk(): JWKSet = JWKSet(jwk)
}
