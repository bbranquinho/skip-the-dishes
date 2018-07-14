package br.com.skip.the.dishes.domain.order.elements;

import br.com.zup.eventsourcing.core.AggregateId;

public interface Handler {

    void on(OrderCreated orderCreated);

    void on(AggregateId aggregateId, ProductAdded productAdded);

    void on(AggregateId aggregateId, ProductDeleted productDeleted);

    void on(AggregateId aggregateId, OrderCancelled orderCancelled);

    void on(AggregateId aggregateId, OrderRequested orderRequested);

    void on(AggregateId aggregateId, DeliveryAddressUpdated deliveryAddressUpdated);

}
