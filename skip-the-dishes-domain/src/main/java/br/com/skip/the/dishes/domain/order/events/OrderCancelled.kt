package br.com.skip.the.dishes.domain.order.events

import br.com.skip.the.dishes.domain.order.OrderStatus
import br.com.skip.the.dishes.domain.order.commons.OrderApplier
import br.com.skip.the.dishes.domain.order.commons.OrderEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class OrderCancelled(val orderStatus: OrderStatus) : Event(), OrderEvent {
    override fun accept(aggregateId: AggregateId?, orderApplier: OrderApplier) {
        orderApplier.on(aggregateId,this)
    }
}

