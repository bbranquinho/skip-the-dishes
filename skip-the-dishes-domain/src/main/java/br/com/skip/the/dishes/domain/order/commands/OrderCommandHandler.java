package br.com.skip.the.dishes.domain.order.commands;

import br.com.skip.the.dishes.domain.order.Order;
import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(OrderCommandHandler.class);

    private final EventStoreRepository<Order> eventRepository;

    public OrderCommandHandler(EventStoreRepository<Order> eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Order createOrder(CreateOrderCommand command) {
        Order order = new Order(command.getCustomerId());
        eventRepository.save(order);

        logger.debug("Product [{}] created.", order.getOrderId());

        return order;
    }

    public void addProduct(AddProductCommand addProductCommand) {
        Order order = findOrder(addProductCommand.getOrderId());

        order.addProduct(addProductCommand.getProductId());

        eventRepository.save(order);

        logger.debug("Product [{}] added on order [{}].", addProductCommand.getProductId(), order.getOrderId());
    }

    public void deleteProduct(DeleteProductCommand deleteProductCommand) {
        Order order = findOrder(deleteProductCommand.getOrderId());

        order.deleteProduct(deleteProductCommand.getProductId());

        eventRepository.save(order);

        logger.debug("Product [{}] deleted for order [{}].", deleteProductCommand.getProductId(), order.getOrderId());
    }

    public void cancelOrder(CancelOrderCommand cancelOrderCommand) {
        Order order = findOrder(cancelOrderCommand.getOrderId());
        order.cancelOrder();

        eventRepository.save(order);
    }

    public void requestOrder(RequestOrderCommand requestOrderCommand) {
        Order order = findOrder(requestOrderCommand.getOrderId());
        order.requestOrder();

        eventRepository.save(order);
    }

    public void updateDeliveryAddress(UpdateDeliveryAddressCommand updateDeliveryAddressCommand) {
        Order order = findOrder(updateDeliveryAddressCommand.getOrderId());
        order.updateDeliveryAddress(updateDeliveryAddressCommand.getDeliveryAddress());

        eventRepository.save(order);
    }

    private Order findOrder(String orderId) {
        AggregateId aggregateId = new AggregateId(orderId);
        return eventRepository.get(aggregateId);
    }

}
