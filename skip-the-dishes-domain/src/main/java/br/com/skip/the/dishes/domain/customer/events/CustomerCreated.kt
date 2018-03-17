package br.com.skip.the.dishes.domain.customer.events

import br.com.skip.the.dishes.domain.customer.commons.CustomerApplier
import br.com.skip.the.dishes.domain.customer.commons.CustomerEvent
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Event

data class CustomerCreated(val customerId: String, val name: String, val email: String, val address: String, val password: String) : Event(), CustomerEvent {
    override fun accept(aggregateId: AggregateId?, customerApplier: CustomerApplier) {
        customerApplier.apply(aggregateId, this)
    }
}
