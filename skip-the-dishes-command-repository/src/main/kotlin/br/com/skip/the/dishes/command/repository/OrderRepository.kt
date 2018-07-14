package br.com.skip.the.dishes.command.repository

import br.com.skip.the.dishes.domain.order.Order
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.springframework.stereotype.Service

@Service
class OrderRepository : EventStoreRepository<Order>()
