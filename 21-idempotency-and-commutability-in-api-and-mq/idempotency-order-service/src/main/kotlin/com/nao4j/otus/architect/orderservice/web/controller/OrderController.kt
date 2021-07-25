package com.nao4j.otus.architect.orderservice.web.controller

import com.nao4j.otus.architect.orderservice.domain.model.Order
import com.nao4j.otus.architect.orderservice.domain.service.OrderService
import com.nao4j.otus.architect.orderservice.web.dto.CreatedOrder
import com.nao4j.otus.architect.orderservice.web.dto.OrderToCreate
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDateTime.now
import java.time.ZoneOffset.UTC

@RestController
@RequestMapping("/v1/orders")
class OrderController(private val orderService: OrderService) {

    @GetMapping
    fun getAll(@RequestHeader("X-User-Id") userId: String): List<CreatedOrder> = orderService.getAll(userId).map { order ->
        CreatedOrder(
            id = order.id,
            receivedAt = order.receivedAt
        )
    }

    @PutMapping
    fun create(
        @RequestHeader("X-User-Id") userId: String,
        @RequestBody orderToCreate: OrderToCreate,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Any> = Order(
        id = orderToCreate.id,
        userId = userId,
        receivedAt = now(UTC)
    ).let(orderService::create)
        .let {
            uriBuilder.path("/v1/orders/{id}").build(orderToCreate.id).let { location -> created(location).build() }
        }
}
