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

    public Order handle(CreateOrderCommand command) {
        Order order = new Order(command.getCustomerId());
        eventRepository.save(order);

        logger.debug("Product [{}] created.", order.getOrderId());

        return order;
    }

    public void handle(AddProductCommand addProductCommand) {
        Order order = findOrder(addProductCommand.getOrderId());

        order.addProduct(addProductCommand.getProductId());

        logger.debug("Product [{}] added on order [{}].", addProductCommand.getProductId(), order.getOrderId());
    }

    public void handle(DeleteProductCommand deleteProductCommand) {
        Order order = findOrder(deleteProductCommand.getOrderId());

        order.deleteProduct(deleteProductCommand.getProductId());

        logger.debug("Product [{}] deleted for order [{}].", deleteProductCommand.getProductId(), order.getOrderId());
    }

    public void handle(CancelOrderCommand cancelOrderCommand) {
        Order order = findOrder(cancelOrderCommand.getOrderId());
        order.cancelOrder();
    }

    public void handle(RequestOrderCommand requestOrderCommand) {
        Order order = findOrder(requestOrderCommand.getOrderId());
        order.requestOrder();
    }

    public void handle(UpdateDeliveryAddressCommand updateDeliveryAddressCommand) {
        Order order = findOrder(updateDeliveryAddressCommand.getOrderId());
        order.updateDeliveryAddress(updateDeliveryAddressCommand.getDeliveryAddress());
    }

    private Order findOrder(String orderId) {
        AggregateId aggregateId = new AggregateId(orderId);
        return eventRepository.get(aggregateId);
    }

}
