package br.com.skip.the.dishes.domain.order.elements

import br.com.zup.eventsourcing.core.AggregateId

interface Handler {

    fun on(orderCreated: OrderCreated)

    fun on(aggregateId: AggregateId, productAdded: ProductAdded)

    fun on(aggregateId: AggregateId, productDeleted: ProductDeleted)

    fun on(aggregateId: AggregateId, orderCancelled: OrderCancelled)

    fun on(aggregateId: AggregateId, orderRequested: OrderRequested)

    fun on(aggregateId: AggregateId, deliveryAddressUpdated: DeliveryAddressUpdated)

}
