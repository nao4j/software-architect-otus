package com.nao4j.otus.architect.orderservice.domain.model

import java.time.LocalDateTime

data class Order(

    val id: String,

    val userId: String,

    val receivedAt: LocalDateTime,

)
