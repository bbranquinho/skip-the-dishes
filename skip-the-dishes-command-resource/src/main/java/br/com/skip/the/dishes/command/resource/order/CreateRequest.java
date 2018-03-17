package br.com.skip.the.dishes.command.resource.order;

public class CreateRequest {

    private String customerId;

    public CreateRequest() {
    }

    public CreateRequest(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
