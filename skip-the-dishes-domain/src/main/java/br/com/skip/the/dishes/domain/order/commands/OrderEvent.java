package br.com.skip.the.dishes.domain.order.commands;

import br.com.zup.eventsourcing.core.AggregateId;

public interface OrderEvent {

    void accept(AggregateId aggregateId, OrderApplier orderApplier);

}
