package br.com.skip.the.dishes.domain.order;

import br.com.skip.the.dishes.domain.order.commons.AttemptChangeOrderStatusException;
import br.com.skip.the.dishes.domain.order.commons.OrderApplier;
import br.com.skip.the.dishes.domain.order.commons.OrderEvent;
import br.com.skip.the.dishes.domain.order.events.*;
import br.com.skip.the.dishes.domain.product.Product;
import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.core.AggregateRoot;
import br.com.zup.eventsourcing.core.Event;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static br.com.skip.the.dishes.domain.order.OrderStatus.*;

public class Order extends AggregateRoot {

    private OrderApplier applier = new Applier();

    private Set<String> products = new HashSet();

    private DeliveryAddress deliveryAddress;

    private String customerId;

    private OrderStatus status;

    public Order() { }

    public Order(String customerId) {
        OrderCreated orderCreatedEvent = new OrderCreated(UUID.randomUUID().toString(), NEW, customerId);
        applyChange(orderCreatedEvent);
    }

    @Override
    public void applyEvent(Event event) {
        ((OrderEvent)event).accept(id, applier);
    }

    public void addProduct(String productId, EventStoreRepository<Product> eventRepository) {
        eventRepository.get(new AggregateId(productId));

        attemptChangeOrder();

        ProductAdded productAddedEvent = new ProductAdded(productId);
        applyChange(productAddedEvent);
    }

    public void deleteProduct(String productId) {
        attemptChangeOrder();

        ProductDeleted productDeletedEvent = new ProductDeleted(productId);
        applyChange(productDeletedEvent);
    }

    public void cancelOrder() {
        OrderCancelled orderCancelledEvent = new OrderCancelled(CANCELLED);
        applyChange(orderCancelledEvent);
    }

    public void requestOrder() {
        attemptChangeOrder();

        OrderRequested orderRequestedEvent = new OrderRequested(FINISHED);
        applyChange(orderRequestedEvent);
    }

    public void updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        attemptChangeOrder();

        DeliveryAddressUpdated deliveryAddressUpdatedEvent = new DeliveryAddressUpdated(deliveryAddress);
        applyChange(deliveryAddressUpdatedEvent);
    }

    private void attemptChangeOrder() {
        if (status != NEW) {
            throw new AttemptChangeOrderStatusException("It is not allowed change order with status " + status);
        }
    }

    private class Applier implements OrderApplier {

        @Override
        public void on(AggregateId aggregateId, OrderCreated orderCreated) {
            id = new AggregateId(orderCreated.getOrderId());
            customerId = orderCreated.getCustomerId();
            status = orderCreated.getStatus();
        }

        @Override
        public void on(AggregateId aggregateId, ProductAdded productAdded) {
            products.add(productAdded.getProductId());
        }

        @Override
        public void on(AggregateId aggregateId, ProductDeleted productDeleted) {
            products.remove(productDeleted.getProductId());
        }

        @Override
        public void on(AggregateId aggregateId, OrderCancelled orderCancelled) {
            status = orderCancelled.getOrderStatus();
        }

        @Override
        public void on(AggregateId aggregateId, OrderRequested orderRequested) {
            status = orderRequested.getOrderStatus();
        }

        @Override
        public void on(AggregateId aggregateId, DeliveryAddressUpdated deliveryAddressUpdated) {
            deliveryAddress = deliveryAddressUpdated.getDeliveryAddress();
        }

    }

    public String getOrderId() {
        return id.getValue();
    }

    public Set<String> getProducts() {
        return products;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
