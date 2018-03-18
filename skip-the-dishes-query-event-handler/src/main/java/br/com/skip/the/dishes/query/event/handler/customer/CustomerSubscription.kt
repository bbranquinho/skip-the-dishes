package br.com.skip.the.dishes.query.event.handler.customer

import br.com.skip.the.dishes.domain.customer.Customer
import br.com.skip.the.dishes.query.event.handler.commons.AggregateSubscriber
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import eventstore.j.EsConnection
import eventstore.j.EsConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CustomerSubscription(
        private val aggregateSubscriber: AggregateSubscriber,
        @Value("\${event.sourcing.subscribe:1}") private val eventSourcingSubscribes: Int,
        eventHandler: CustomerEventHandler
) : PersistentAggregateSubscriber<Customer>(eventHandler = eventHandler) {

    private val esConnection: EsConnection = EsConnectionFactory.create(actorSystem)

    override fun start() {
        aggregateSubscriber.start(
                aggregateRootName = Customer::class.java.simpleName,
                actorSystem = actorSystem,
                esConnection = esConnection
        )

        (1..eventSourcingSubscribes).forEach { super.start() }
    }

}
