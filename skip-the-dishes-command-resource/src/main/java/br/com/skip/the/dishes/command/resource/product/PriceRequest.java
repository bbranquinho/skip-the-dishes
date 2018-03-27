package br.com.skip.the.dishes.command.resource.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_PRICE_MIN;

public class PriceRequest {

    @NotNull
    @Min(PRODUCT_PRICE_MIN)
    private Double price;

    public PriceRequest() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
