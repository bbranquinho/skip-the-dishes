package br.com.skip.the.dishes.query.event.handler.order

import br.com.skip.the.dishes.domain.order.Order
import br.com.skip.the.dishes.query.event.handler.commons.AggregateSubscriber
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import eventstore.j.EsConnection
import eventstore.j.EsConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OrderSubscription(
        private val aggregateSubscriber: AggregateSubscriber,
        @Value("\${event.sourcing.subscribe:1}") private val eventSourcingSubscribes: Int,
        eventHandler: OrderEventHandler
) : PersistentAggregateSubscriber<Order>(eventHandler = eventHandler) {

    private val esConnection: EsConnection = EsConnectionFactory.create(actorSystem)

    override fun start() {
        aggregateSubscriber.start(
                aggregateRootName = Order::class.java.simpleName,
                actorSystem = actorSystem,
                esConnection = esConnection
        )

        (1..eventSourcingSubscribes).forEach { super.start() }
    }

}
