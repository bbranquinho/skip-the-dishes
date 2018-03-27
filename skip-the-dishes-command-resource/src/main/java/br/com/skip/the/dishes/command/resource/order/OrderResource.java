package br.com.skip.the.dishes.command.resource.order;

import br.com.skip.the.dishes.domain.order.DeliveryAddress;
import br.com.skip.the.dishes.domain.order.Order;
import br.com.skip.the.dishes.domain.order.commands.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_ID_SIZE;

@RestController
public class OrderResource implements OrderApi {

    private final OrderCommandHandler orderCommandHandler;

    public OrderResource(OrderCommandHandler orderCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
    }

    @Override
    public CreateResponse create(@RequestBody @Valid CreateRequest createRequest) {
        CreateOrderCommand command = new CreateOrderCommand(createRequest.getCustomerId());
        Order order = orderCommandHandler.handle(command);
        return new CreateResponse(order.getOrderId());
    }

    @Override
    public void addProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id,
                           @RequestBody @Valid ProductRequest productRequest) {
        AddProductCommand command = new AddProductCommand(id, productRequest.getProductId());
        orderCommandHandler.handle(command);
    }

    @Override
    public void deleteProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id,
                              @RequestBody @Valid ProductRequest productRequest) {
        DeleteProductCommand command = new DeleteProductCommand(id, productRequest.getProductId());
        orderCommandHandler.handle(command);
    }

    @Override
    public void cancelOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id) {
        CancelOrderCommand command = new CancelOrderCommand(id);
        orderCommandHandler.handle(command);
    }

    @Override
    public void requestOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id) {
        RequestOrderCommand command = new RequestOrderCommand(id);
        orderCommandHandler.handle(command);
    }

    @Override
    public void updateDeliveryAddress(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id,
                                      @RequestBody @Valid DeliveryAddressRequest deliveryAddressRequest) {
        DeliveryAddress deliveryAddress = new DeliveryAddress(deliveryAddressRequest.getDeliveryAddress(), deliveryAddressRequest.getContact());
        UpdateDeliveryAddressCommand command = new UpdateDeliveryAddressCommand(id, deliveryAddress);
        orderCommandHandler.handle(command);
    }

}
