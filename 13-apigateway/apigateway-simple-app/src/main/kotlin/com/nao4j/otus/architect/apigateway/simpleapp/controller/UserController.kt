package com.nao4j.otus.architect.apigateway.simpleapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/user/me")
    fun getUserInfo(
        @RequestHeader("X-User-Login") login: String,
        @RequestHeader("X-User-First-Name") firstName: String,
        @RequestHeader("X-User-Last-Name") lastName: String
    ) = UserInfo(login, firstName, lastName)
}

data class UserInfo(val login: String, val firstName: String, val lastName: String)
