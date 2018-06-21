package br.com.skip.the.dishes.domain.order.elements

import br.com.skip.the.dishes.domain.order.DeliveryAddress

data class AddProductCommand(val orderId: String, val productId: String)

data class CancelOrderCommand(val orderId: String)

data class CreateOrderCommand(val customerId: String)

data class DeleteProductCommand(val orderId: String, val productId: String)

data class RequestOrderCommand(val orderId: String)

data class UpdateDeliveryAddressCommand(val orderId: String, val deliveryAddress: DeliveryAddress)

