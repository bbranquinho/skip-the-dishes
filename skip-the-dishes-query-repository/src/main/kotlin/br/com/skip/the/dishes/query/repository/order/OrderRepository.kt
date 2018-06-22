package br.com.skip.the.dishes.query.repository.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, String>
