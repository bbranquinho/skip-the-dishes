package br.com.skip.the.dishes.command.resource.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.*;

public class CreateRequest {

    @NotNull
    @NotEmpty
    @Size(max = PRODUCT_NAME_SIZE)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = PRODUCT_DESCRIPTION_SIZE)
    private String description;

    @NotNull
    @NotEmpty
    @Size(max = PRODUCT_STORE_ID_SIZE)
    private String storeId;

    @NotNull
    @Min(PRODUCT_PRICE_MIN)
    private Double price;

    public CreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
