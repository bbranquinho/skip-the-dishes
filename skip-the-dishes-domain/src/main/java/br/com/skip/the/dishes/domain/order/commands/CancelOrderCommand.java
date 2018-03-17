package br.com.skip.the.dishes.domain.order.commands;

public class CancelOrderCommand {

    private String orderId;

    public CancelOrderCommand() {
    }

    public CancelOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
