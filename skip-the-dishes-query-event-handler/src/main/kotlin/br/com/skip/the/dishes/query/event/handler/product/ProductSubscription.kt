package br.com.skip.the.dishes.query.event.handler.product


import br.com.skip.the.dishes.domain.product.Product
import br.com.skip.the.dishes.query.event.handler.commons.AggregateSubscriber
import br.com.zup.eventsourcing.core.EventHandler
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber
import eventstore.j.EsConnection
import eventstore.j.EsConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ProductSubscription(
        private val aggregateSubscriber: AggregateSubscriber,
        @Value("\${event.sourcing.subscribe:1}") private val eventSourcingSubscribes: Int,
        eventHandler: ProductEventHandler
) : PersistentAggregateSubscriber<Product>(eventHandler = eventHandler) {

    private val esConnection: EsConnection = EsConnectionFactory.create(actorSystem)

    override fun start() {
        aggregateSubscriber.start(
                aggregateRootName = Product::class.java.simpleName,
                actorSystem = actorSystem,
                esConnection = esConnection
        )

        (1..eventSourcingSubscribes).forEach { super.start() }
    }

}

