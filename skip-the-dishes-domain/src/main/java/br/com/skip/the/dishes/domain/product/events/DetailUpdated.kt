package br.com.skip.the.dishes.domain.product.events

import br.com.skip.the.dishes.domain.product.Detail
import br.com.skip.the.dishes.domain.product.commons.ProductApplier
import br.com.skip.the.dishes.domain.product.commons.ProductEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class DetailUpdated(val detail: Detail) : Event(), ProductEvent {
    override fun accept(aggregateId: AggregateId, productApplier: ProductApplier) {
        productApplier.on(aggregateId,this)
    }
}
