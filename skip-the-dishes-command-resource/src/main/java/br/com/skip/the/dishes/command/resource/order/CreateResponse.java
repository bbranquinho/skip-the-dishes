package br.com.skip.the.dishes.command.resource.order;

public class CreateResponse {

    private String orderId;

    public CreateResponse() {
    }

    public CreateResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
