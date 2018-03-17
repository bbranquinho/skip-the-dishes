package br.com.skip.the.dishes.command.resource.order;

public class ProductRequest {

    private String productId;

    public ProductRequest() {
    }

    public ProductRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
