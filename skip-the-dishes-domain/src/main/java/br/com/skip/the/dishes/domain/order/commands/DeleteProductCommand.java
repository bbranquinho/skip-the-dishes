package br.com.skip.the.dishes.domain.order.commands;

public class DeleteProductCommand {

    private String orderId;

    private String productId;

    public DeleteProductCommand() {
    }

    public DeleteProductCommand(String orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
