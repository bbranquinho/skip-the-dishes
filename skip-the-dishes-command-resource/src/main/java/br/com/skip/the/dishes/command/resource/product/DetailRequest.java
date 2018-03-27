package br.com.skip.the.dishes.command.resource.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_DESCRIPTION_SIZE;
import static br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_NAME_SIZE;

public class DetailRequest {

    @NotNull
    @NotEmpty
    @Size(max = PRODUCT_NAME_SIZE)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = PRODUCT_DESCRIPTION_SIZE)
    private String description;

    public DetailRequest() {
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
}
