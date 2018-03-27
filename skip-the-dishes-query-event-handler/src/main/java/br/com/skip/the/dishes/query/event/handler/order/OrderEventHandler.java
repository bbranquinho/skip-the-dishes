package br.com.skip.the.dishes.query.event.handler.order;

import br.com.skip.the.dishes.domain.order.DeliveryAddress;
import br.com.skip.the.dishes.domain.order.commons.OrderApplier;
import br.com.skip.the.dishes.domain.order.commons.OrderEvent;
import br.com.skip.the.dishes.domain.order.events.*;
import br.com.skip.the.dishes.query.repository.order.OrderEntity;
import br.com.skip.the.dishes.query.repository.order.OrderRepository;
import br.com.skip.the.dishes.query.repository.product.ProductEntity;
import br.com.skip.the.dishes.query.repository.product.ProductRepository;
import br.com.zup.eventsourcing.core.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderEventHandler implements EventHandler {

    private OrderApplier applier = new Applier();

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    public OrderEventHandler(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void handle(AggregateId aggregateId, Event event, MetaData metaData, AggregateVersion aggregateVersion) {
        ((OrderEvent)event).accept(aggregateId, applier);
    }

    private class Applier implements OrderApplier {
        @Override
        public void on(AggregateId aggregateId, OrderCreated orderCreated) {
            OrderEntity orderEntity = new OrderEntity(aggregateId.getValue(), orderCreated.getCustomerId());
            orderEntity.setStatus(orderCreated.getStatus().toString());
            orderRepository.save(orderEntity);
        }

        @Override
        public void on(AggregateId aggregateId, ProductAdded productAdded) {
            OrderEntity orderEntity = orderRepository.findById(aggregateId.getValue()).get();
            ProductEntity productEntity = productRepository.findById(productAdded.getProductId()).get();

            orderEntity.getProducts().add(productEntity);

            orderRepository.save(orderEntity);
        }

        @Override
        public void on(AggregateId aggregateId, ProductDeleted productDeleted) {
            OrderEntity orderEntity = orderRepository.findById(aggregateId.getValue()).get();
            ProductEntity productEntity = productRepository.findById(productDeleted.getProductId()).get();

            orderEntity.getProducts().remove(productEntity);

            orderRepository.save(orderEntity);
        }

        @Override
        public void on(AggregateId aggregateId, OrderCancelled orderCancelled) {
            OrderEntity orderEntity = orderRepository.findById(aggregateId.getValue()).get();
            orderEntity.setStatus(orderCancelled.getOrderStatus().toString());
            orderRepository.save(orderEntity);
        }

        @Override
        public void on(AggregateId aggregateId, OrderRequested orderRequested) {
            OrderEntity orderEntity = orderRepository.findById(aggregateId.getValue()).get();
            orderEntity.setStatus(orderRequested.getOrderStatus().toString());
            orderRepository.save(orderEntity);
        }

        @Override
        public void on(AggregateId aggregateId, DeliveryAddressUpdated deliveryAddressUpdated) {
            OrderEntity orderEntity = orderRepository.findById(aggregateId.getValue()).get();
            DeliveryAddress deliveryAddress = deliveryAddressUpdated.getDeliveryAddress();
            orderEntity.setDeliveryAddress(deliveryAddress.getDeliveryAddress());
            orderEntity.setContact(deliveryAddress.getContact());
            orderRepository.save(orderEntity);
        }
    }
}

