package br.com.skip.the.dishes.command.resource.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.CUSTOMER_ID_SIZE;

public class CreateRequest {

    @NotEmpty
    @Size(max = CUSTOMER_ID_SIZE)
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
