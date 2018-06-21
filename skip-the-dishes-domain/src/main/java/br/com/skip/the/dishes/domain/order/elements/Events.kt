package br.com.skip.the.dishes.domain.order.elements

import br.com.skip.the.dishes.domain.order.DeliveryAddress
import br.com.skip.the.dishes.domain.order.OrderStatus
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

sealed class OrderEvent : Event() {
    fun apply(aggregateId: AggregateId, handler: Handler) =
        when (this) {
            is DeliveryAddressUpdated -> handler.on(aggregateId, this)
            is OrderCancelled -> handler.on(aggregateId, this)
            is OrderRequested -> handler.on(aggregateId, this)
            is ProductAdded -> handler.on(aggregateId, this)
            is ProductDeleted -> handler.on(aggregateId, this)
        }
}

data class OrderCreated(val orderId: String, val status: OrderStatus, val customerId: String) : Event()

data class DeliveryAddressUpdated(val deliveryAddress: DeliveryAddress) : OrderEvent()

data class OrderCancelled(val orderStatus: OrderStatus) : OrderEvent()

data class OrderRequested(val orderStatus: OrderStatus) : OrderEvent()

data class ProductAdded(val productId: String)  : OrderEvent()

data class ProductDeleted(val productId: String)  : OrderEvent()
