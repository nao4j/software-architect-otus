package com.nao4j.otus.architect.apigateway.idently.repostory

import com.nao4j.otus.architect.apigateway.idently.model.AuthUser
import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Propagation.MANDATORY
import org.springframework.transaction.annotation.Transactional

interface AuthUserRepository : Repository<AuthUser, String> {
    fun findByLoginAndPassword(login: String, password: String): AuthUser?

    @Transactional(propagation = MANDATORY)
    fun save(authUser: AuthUser): AuthUser
}
