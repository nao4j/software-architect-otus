package com.nao4j.otus.architect.orderservice.dal.dao

import com.nao4j.otus.architect.orderservice.dal.row.OrderRow
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.ResultSet

@Repository
class OrderDaoImpl(private val jdbcTemplate: JdbcTemplate) : OrderDao {

    private val insert = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("orders")
        .usingColumns("id", "user_id", "received_at")

    override fun findAllByUserId(userId: String): List<OrderRow> = jdbcTemplate.query(FIND_ALL_BY_USER_ID, rowMapper, userId)

    @Transactional
    override fun create(order: OrderRow) {
        val params = mapOf(
            "id" to order.id,
            "user_id" to order.userId,
            "received_at" to order.receivedAt
        )
        insert.execute(params).let {
            if (it != 1) throw IncorrectResultSizeDataAccessException(1, it)
        }
    }

    companion object {

        private const val FIND_ALL_BY_USER_ID = """
            SELECT id, user_id, received_at
            FROM orders
            WHERE user_id = ?
        """

        private val rowMapper = RowMapper<OrderRow> { resultSet: ResultSet, _ ->
            OrderRow(
                id = resultSet.getString("id"),
                userId = resultSet.getString("user_id"),
                receivedAt = resultSet.getTimestamp("received_at").toLocalDateTime()
            )
        }

    }

}
