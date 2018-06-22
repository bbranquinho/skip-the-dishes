package br.com.skip.the.dishes.query.event.handler.order

import br.com.skip.the.dishes.domain.order.elements.*
import br.com.skip.the.dishes.query.repository.order.OrderEntity
import br.com.skip.the.dishes.query.repository.order.OrderRepository
import br.com.skip.the.dishes.query.repository.product.ProductRepository
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.AggregateVersion
import br.com.zup.eventsourcing.core.Event
import br.com.zup.eventsourcing.core.EventHandler
import br.com.zup.eventsourcing.core.MetaData
import org.springframework.stereotype.Component

@Component
class OrderEventHandler(private val orderRepository: OrderRepository, private val productRepository: ProductRepository) : EventHandler, Handler {

    override fun handle(aggregateId: AggregateId, event: Event, metaData: MetaData, version: AggregateVersion) {
        (event as OrderEvent).apply(aggregateId, this)
    }

    override fun on(orderCreated: OrderCreated) {
        val orderEntity = OrderEntity(
                id = orderCreated.orderId,
                customerId = orderCreated.customerId,
                status = orderCreated.status.toString()
        )
        orderRepository.save(orderEntity)
    }

    override fun on(aggregateId: AggregateId, productAdded: ProductAdded) {
        val productEntity = productRepository.findById(productAdded.productId).get()

        orderRepository
                .findById(aggregateId.value)
                .get()
                .let { it.copy(products = it.products + productEntity) }
                .also { orderRepository.save(it) }
    }

    override fun on(aggregateId: AggregateId, productDeleted: ProductDeleted) {
        val productEntity = productRepository.findById(productDeleted.productId).get()

        orderRepository
                .findById(aggregateId.value)
                .get()
                .let { it.copy(products = it.products.filter { it.id != productEntity.id }) }
                .also { orderRepository.save(it) }
    }

    override fun on(aggregateId: AggregateId, orderCancelled: OrderCancelled) {
        orderRepository
                .findById(aggregateId.value)
                .get()
                .let { it.copy(status = orderCancelled.orderStatus.toString()) }
                .also { orderRepository.save(it) }
    }

    override fun on(aggregateId: AggregateId, orderRequested: OrderRequested) {
        orderRepository
                .findById(aggregateId.value)
                .get()
                .let { it.copy(status = orderRequested.orderStatus.toString()) }
                .also { orderRepository.save(it) }
    }

    override fun on(aggregateId: AggregateId, deliveryAddressUpdated: DeliveryAddressUpdated) {
        orderRepository.findById(aggregateId.value).get()
                .let {
                    it.copy(
                            deliveryAddress = deliveryAddressUpdated.deliveryAddress.deliveryAddress,
                            contact = deliveryAddressUpdated.deliveryAddress.contact
                    )
                }
                .also { orderRepository.save(it) }
    }
}

