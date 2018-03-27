package br.com.skip.the.dishes.domain.order.commands

data class AddProductCommand(val orderId: String, val productId: String)
