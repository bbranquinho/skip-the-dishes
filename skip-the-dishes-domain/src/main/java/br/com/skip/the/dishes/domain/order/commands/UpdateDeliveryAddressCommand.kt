package br.com.skip.the.dishes.domain.order.commands

import br.com.skip.the.dishes.domain.order.DeliveryAddress

data class UpdateDeliveryAddressCommand(val orderId: String, val deliveryAddress: DeliveryAddress)
