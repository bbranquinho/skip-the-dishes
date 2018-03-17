package br.com.skip.the.dishes.domain.order.commands;

import br.com.skip.the.dishes.domain.order.DeliveryAddress;

public class UpdateDeliveryAddressCommand {

    private String orderId;

    private DeliveryAddress deliveryAddress;

    public UpdateDeliveryAddressCommand() {
    }

    public UpdateDeliveryAddressCommand(String orderId, DeliveryAddress deliveryAddress) {
        this.orderId = orderId;
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
