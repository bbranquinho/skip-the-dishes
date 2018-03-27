package br.com.skip.the.dishes.domain.order.commands

data class DeleteProductCommand(val orderId: String, val productId: String)
