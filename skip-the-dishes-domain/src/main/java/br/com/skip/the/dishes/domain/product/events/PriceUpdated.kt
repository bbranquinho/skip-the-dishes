package br.com.skip.the.dishes.domain.product.events

import br.com.skip.the.dishes.domain.product.commons.ProductApplier
import br.com.skip.the.dishes.domain.product.commons.ProductEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class PriceUpdated(val price: Double) : Event(), ProductEvent {
    override fun accept(aggregateId: AggregateId, productApplier: ProductApplier) {
        productApplier.apply(aggregateId,this)
    }
}
