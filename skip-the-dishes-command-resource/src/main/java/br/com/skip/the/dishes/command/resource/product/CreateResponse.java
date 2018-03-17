package br.com.skip.the.dishes.command.resource.product;

public class CreateResponse {

    private String productId;

    public CreateResponse() {
    }

    public CreateResponse(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
