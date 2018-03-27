package br.com.skip.the.dishes.domain.order.commands

import br.com.skip.the.dishes.domain.order.Order
import br.com.skip.the.dishes.domain.product.Product
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.slf4j.LoggerFactory

class OrderCommandHandler(private val orderEventRepository: EventStoreRepository<Order>,
                          private val productEventRepository: EventStoreRepository<Product>) {

    private val logger = LoggerFactory.getLogger(OrderCommandHandler::class.java)

    fun handle(command: CreateOrderCommand): Order {
        val order = Order(command.customerId)

        orderEventRepository.save(order)

        logger.debug("Product [{}] created.", order.orderId)

        return order
    }

    fun handle(addProductCommand: AddProductCommand) {
        val order = findOrder(addProductCommand.orderId)

        order.addProduct(addProductCommand.productId, productEventRepository)

        orderEventRepository.save(order)

        logger.debug("Product [{}] added on order [{}].", addProductCommand.productId, order.orderId)
    }

    fun handle(deleteProductCommand: DeleteProductCommand) {
        val order = findOrder(deleteProductCommand.orderId)

        order.deleteProduct(deleteProductCommand.productId)

        orderEventRepository.save(order)

        logger.debug("Product [{}] deleted for order [{}].", deleteProductCommand.productId, order.orderId)
    }

    fun handle(cancelOrderCommand: CancelOrderCommand) {
        val order = findOrder(cancelOrderCommand.orderId)
        order.cancelOrder()

        orderEventRepository.save(order)
    }

    fun handle(requestOrderCommand: RequestOrderCommand) {
        val order = findOrder(requestOrderCommand.orderId)
        order.requestOrder()

        orderEventRepository.save(order)
    }

    fun handle(updateDeliveryAddressCommand: UpdateDeliveryAddressCommand) {
        val order = findOrder(updateDeliveryAddressCommand.orderId)
        order.updateDeliveryAddress(updateDeliveryAddressCommand.deliveryAddress)

        orderEventRepository.save(order)
    }

    private fun findOrder(orderId: String): Order {
        val aggregateId = AggregateId(orderId)
        return orderEventRepository.get(aggregateId)
    }

}
