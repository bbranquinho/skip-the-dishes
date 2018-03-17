package br.com.skip.the.dishes.domain.order.commands;

import br.com.skip.the.dishes.domain.order.events.OrderCreated;
import br.com.zup.eventsourcing.core.AggregateId;

public interface OrderApplier {

    void apply(AggregateId aggregateId, OrderCreated orderCreated);

}
