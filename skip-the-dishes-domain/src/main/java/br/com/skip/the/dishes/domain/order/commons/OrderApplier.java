package br.com.skip.the.dishes.domain.order.commons;

import br.com.skip.the.dishes.domain.order.events.*;
import br.com.zup.eventsourcing.core.AggregateId;

public interface OrderApplier {

    void on(AggregateId aggregateId, OrderCreated orderCreated);

    void on(AggregateId aggregateId, ProductAdded productAdded);

    void on(AggregateId aggregateId, ProductDeleted productDeleted);

    void on(AggregateId aggregateId, OrderCancelled orderCancelled);

    void on(AggregateId aggregateId, OrderRequested orderRequested);

    void on(AggregateId aggregateId, DeliveryAddressUpdated deliveryAddressUpdated);

}
