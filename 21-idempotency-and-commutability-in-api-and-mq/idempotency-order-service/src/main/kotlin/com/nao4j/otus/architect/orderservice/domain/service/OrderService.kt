package com.nao4j.otus.architect.orderservice.domain.service

import com.nao4j.otus.architect.orderservice.domain.model.Order

interface OrderService {

    fun getAll(userId: String): List<Order>

    fun create(order: Order)

}
