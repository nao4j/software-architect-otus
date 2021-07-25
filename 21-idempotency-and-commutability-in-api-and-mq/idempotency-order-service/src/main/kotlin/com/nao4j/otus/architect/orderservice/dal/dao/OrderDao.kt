package com.nao4j.otus.architect.orderservice.dal.dao

import com.nao4j.otus.architect.orderservice.dal.row.OrderRow

interface OrderDao {

    fun findAllByUserId(userId: String): List<OrderRow>

    fun create(order: OrderRow)

}
