package br.com.skip.the.dishes.domain.order.events

import br.com.skip.the.dishes.domain.order.DeliveryAddress
import br.com.skip.the.dishes.domain.order.commons.OrderApplier
import br.com.skip.the.dishes.domain.order.commons.OrderEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class DeliveryAddressUpdated(val deliveryAddress: DeliveryAddress) : Event(), OrderEvent {
    override fun accept(aggregateId: AggregateId?, orderApplier: OrderApplier) {
        orderApplier.on(aggregateId,this)
    }
}
