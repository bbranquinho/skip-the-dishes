package br.com.skip.the.dishes.domain.order.commands;

public class RequestOrderCommand {

    private String orderId;

    public RequestOrderCommand() {
    }

    public RequestOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
