package br.com.skip.the.dishes.domain.order.commands;

public class CreateOrderCommand {

    private String customerId;

    public CreateOrderCommand() {
    }

    public CreateOrderCommand(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
