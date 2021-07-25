package com.nao4j.otus.architect.orderservice.domain.service

import com.nao4j.otus.architect.orderservice.dal.dao.OrderDao
import com.nao4j.otus.architect.orderservice.dal.row.OrderRow
import com.nao4j.otus.architect.orderservice.domain.model.Order
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val orderDao: OrderDao) : OrderService {

    override fun getAll(userId: String): List<Order> = orderDao.findAllByUserId(userId).map { row ->
        Order(
            id = row.id,
            userId = row.userId,
            receivedAt = row.receivedAt
        )
    }

    override fun create(order: Order) = kotlin.runCatching {
        orderDao.create(
            OrderRow(
                id = order.id,
                userId = order.userId,
                receivedAt = order.receivedAt
            )
        )
    }.getOrElse { e ->
        when (e) {
            is DuplicateKeyException -> Unit
            else -> throw e
        }
    }

}
