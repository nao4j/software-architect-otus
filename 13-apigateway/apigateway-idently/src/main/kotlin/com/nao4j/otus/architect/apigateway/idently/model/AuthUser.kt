package com.nao4j.otus.architect.apigateway.idently.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Immutable
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Immutable
@Table("auth_users")
data class AuthUser @PersistenceConstructor constructor(

    @Id
    var id: Long = 0,

    @field:Column("login")
    val login: String,

    @field:Column("password")
    val password: String,

    @field:Column("first_name")
    val firstName: String,

    @field:Column("last_name")
    val lastName: String

)
