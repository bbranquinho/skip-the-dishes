package br.com.skip.the.dishes.domain.order.events

import br.com.skip.the.dishes.domain.order.commands.OrderApplier
import br.com.skip.the.dishes.domain.order.commands.OrderEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class OrderCreated(val customerId: String) : Event(), OrderEvent {
    override fun accept(aggregateId: AggregateId?, orderApplier: OrderApplier) {
        orderApplier.apply(aggregateId,this)
    }
}
