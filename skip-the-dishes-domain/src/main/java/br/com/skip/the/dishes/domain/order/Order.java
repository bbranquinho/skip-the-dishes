package br.com.skip.the.dishes.domain.order;

import br.com.skip.the.dishes.domain.order.commands.OrderEvent;
import br.com.skip.the.dishes.domain.order.events.OrderCreated;
import br.com.skip.the.dishes.domain.product.commons.ProductEvent;
import br.com.zup.eventsourcing.core.AggregateRoot;
import br.com.zup.eventsourcing.core.Event;

import java.util.List;

public class Order extends AggregateRoot {

    private List<String> products;

    private String customerId;

    private OrderStatus status;

    public Order() { }

    public Order(String customerId) {
        OrderCreated orderCreatedEvent = new OrderCreated(customerId);
        applyChange(orderCreatedEvent);
    }

    @Override
    public void applyEvent(Event event) {
        //((OrderEvent)event).accept(id, applier);
    }


}
