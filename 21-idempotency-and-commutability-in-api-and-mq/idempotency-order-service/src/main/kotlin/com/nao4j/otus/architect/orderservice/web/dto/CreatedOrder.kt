package com.nao4j.otus.architect.orderservice.web.dto

import java.time.LocalDateTime

data class CreatedOrder(

    val id: String,

    val receivedAt: LocalDateTime,

)
