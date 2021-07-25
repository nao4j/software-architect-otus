package com.nao4j.otus.architect.orderservice.dal.row

import java.time.LocalDateTime

data class OrderRow(

    val id: String,

    val userId: String,

    val receivedAt: LocalDateTime

)
