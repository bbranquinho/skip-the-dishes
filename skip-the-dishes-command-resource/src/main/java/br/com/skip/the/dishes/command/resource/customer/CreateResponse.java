package br.com.skip.the.dishes.command.resource.customer;

public class CreateResponse {

    private String customerId;

    public CreateResponse() {
    }

    public CreateResponse(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
