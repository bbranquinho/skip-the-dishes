package br.com.skip.the.dishes.domain.product.events

import br.com.skip.the.dishes.domain.product.commons.ProductApplier
import br.com.skip.the.dishes.domain.product.commons.ProductEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class ProductCreated(val productId: String, val name: String, val description: String, val storeId: String, val price: Double) : Event(), ProductEvent {
    override fun accept(productApplier: ProductApplier) { productApplier.apply(this) }
}
