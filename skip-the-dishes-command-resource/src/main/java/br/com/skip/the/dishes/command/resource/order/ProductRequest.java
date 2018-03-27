package br.com.skip.the.dishes.command.resource.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_ID_SIZE;

public class ProductRequest {

    @NotNull
    @NotEmpty
    @Size(max = PRODUCT_ID_SIZE)
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
