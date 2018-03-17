package br.com.skip.the.dishes.domain.order.commons;

import br.com.skip.the.dishes.domain.order.events.*;
import br.com.zup.eventsourcing.core.AggregateId;

public interface OrderApplier {

    void apply(AggregateId aggregateId, OrderCreated orderCreated);

    void apply(AggregateId aggregateId, ProductAdded productAdded);

    void apply(AggregateId aggregateId, ProductDeleted productDeleted);

    void apply(AggregateId aggregateId, OrderCancelled orderCancelled);

    void apply(AggregateId aggregateId, OrderRequested orderRequested);

    void apply(AggregateId aggregateId, DeliveryAddressUpdated deliveryAddressUpdated);

}
