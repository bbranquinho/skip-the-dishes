package br.com.skip.the.dishes.command.resource.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_CONTACT_SIZE;
import static br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_DELIVERY_ADDRESS_SIZE;

public class DeliveryAddressRequest {

    @NotNull
    @NotEmpty
    @Size(max = ORDER_DELIVERY_ADDRESS_SIZE)
    private String deliveryAddress;

    @NotNull
    @NotEmpty
    @Size(max = ORDER_CONTACT_SIZE)
    private String contact;

    public DeliveryAddressRequest() {
    }

    public DeliveryAddressRequest(String deliveryAddress, String contact) {
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
