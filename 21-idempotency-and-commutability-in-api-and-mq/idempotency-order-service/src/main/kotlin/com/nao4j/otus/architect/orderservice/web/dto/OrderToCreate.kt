package com.nao4j.otus.architect.orderservice.web.dto

data class OrderToCreate(

    val id: String,

    val items: List<OrderItem>

)
