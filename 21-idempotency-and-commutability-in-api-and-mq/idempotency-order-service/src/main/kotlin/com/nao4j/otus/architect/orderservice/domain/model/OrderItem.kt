package com.nao4j.otus.architect.orderservice.domain.model

import java.math.BigDecimal

data class OrderItem(

    val productId: Long,

    val pricePerItem: BigDecimal,

    val quantity: Int

)
