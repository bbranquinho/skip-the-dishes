package br.com.skip.the.dishes.domain.order.elements

import br.com.skip.the.dishes.domain.order.Order
import br.com.skip.the.dishes.domain.product.Product
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.slf4j.LoggerFactory


class CommandHandler(private val orderEventRepository: EventStoreRepository<Order>,
                     private val productEventRepository: EventStoreRepository<Product>) {

    private val logger = LoggerFactory.getLogger(CommandHandler::class.java)

    fun handle(command: CreateOrderCommand): Order {
        val order = Order(command.customerId)
                .also { orderEventRepository.save(it) }

        logger.debug("Product [{}] created.", order.getOrderId())

        return order
    }

    fun handle(addProductCommand: AddProductCommand) {
        findOrder(addProductCommand.orderId)
                .apply { addProduct(addProductCommand.productId, productEventRepository) }
                .also(orderEventRepository::save)

        logger.debug("Product [{}] added on order [{}].", addProductCommand.productId, addProductCommand.orderId)
    }

    fun handle(deleteProductCommand: DeleteProductCommand) {
        findOrder(deleteProductCommand.orderId)
                .apply { deleteProduct(deleteProductCommand.productId) }
                .also(orderEventRepository::save)

        logger.debug("Product [{}] deleted for order [{}].", deleteProductCommand.productId, deleteProductCommand.orderId)
    }

    fun handle(cancelOrderCommand: CancelOrderCommand) {
        findOrder(cancelOrderCommand.orderId)
                .apply { cancelOrder() }
                .also(orderEventRepository::save)
    }

    fun handle(requestOrderCommand: RequestOrderCommand) {
        findOrder(requestOrderCommand.orderId)
                .apply { requestOrder() }
                .also(orderEventRepository::save)
    }

    fun handle(updateDeliveryAddressCommand: UpdateDeliveryAddressCommand) {
        findOrder(updateDeliveryAddressCommand.orderId)
                .apply { updateDeliveryAddress(updateDeliveryAddressCommand.deliveryAddress) }
                .also(orderEventRepository::save)
    }

    private fun findOrder(orderId: String): Order =
            orderEventRepository.get(AggregateId(orderId))

}
