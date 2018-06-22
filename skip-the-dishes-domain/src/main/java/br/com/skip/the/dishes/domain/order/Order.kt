package br.com.skip.the.dishes.domain.order

import br.com.skip.the.dishes.domain.order.OrderStatus.*
import br.com.skip.the.dishes.domain.order.elements.AttemptChangeOrderStatusException
import br.com.skip.the.dishes.domain.order.elements.Handler
import br.com.skip.the.dishes.domain.order.elements.*
import br.com.skip.the.dishes.domain.product.Product
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.AggregateRoot
import br.com.zup.eventsourcing.core.Event
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import java.util.*

class Order : AggregateRoot, Handler {

    val products = mutableSetOf<String>()

    var deliveryAddress: DeliveryAddress? = null
        private set

    lateinit var customerId: String
        private set

    lateinit var status: OrderStatus
        private set

    constructor()

    constructor(customerId: String) {
        applyChange(OrderCreated(UUID.randomUUID().toString(), NEW, customerId))
    }

    fun addProduct(productId: String, eventRepository: EventStoreRepository<Product>) {
        eventRepository.get(AggregateId(productId))

        attemptChangeOrder()

        val productAddedEvent = ProductAdded(productId)
        applyChange(productAddedEvent)
    }

    fun deleteProduct(productId: String) {
        attemptChangeOrder()

        val productDeletedEvent = ProductDeleted(productId)
        applyChange(productDeletedEvent)
    }

    fun cancelOrder() {
        val orderCancelledEvent = OrderCancelled(CANCELLED)
        applyChange(orderCancelledEvent)
    }

    fun requestOrder() {
        attemptChangeOrder()

        val orderRequestedEvent = OrderRequested(FINISHED)
        applyChange(orderRequestedEvent)
    }

    fun updateDeliveryAddress(deliveryAddress: DeliveryAddress) {
        attemptChangeOrder()

        val deliveryAddressUpdatedEvent = DeliveryAddressUpdated(deliveryAddress)
        applyChange(deliveryAddressUpdatedEvent)
    }

    private fun attemptChangeOrder() {
        if (status != NEW) {
            throw AttemptChangeOrderStatusException("It is not allowed change order with status $status")
        }
    }

    override fun applyEvent(event: Event) {
        if (event is OrderCreated) {
            on(event)
        } else {
            (event as OrderEvent).apply(id, this)
        }
    }

    override fun on(orderCreated: OrderCreated) {
        id = AggregateId(orderCreated.orderId)
        customerId = orderCreated.customerId
        status = orderCreated.status
    }

    override fun on(aggregateId: AggregateId, productAdded: ProductAdded) {
        products.add(productAdded.productId)
    }

    override fun on(aggregateId: AggregateId, productDeleted: ProductDeleted) {
        products.remove(productDeleted.productId)
    }

    override fun on(aggregateId: AggregateId, orderCancelled: OrderCancelled) {
        status = orderCancelled.orderStatus
    }

    override fun on(aggregateId: AggregateId, orderRequested: OrderRequested) {
        status = orderRequested.orderStatus
    }

    override fun on(aggregateId: AggregateId, deliveryAddressUpdated: DeliveryAddressUpdated) {
        deliveryAddress = deliveryAddressUpdated.deliveryAddress
    }

    fun getOrderId() =
            id.value

}
