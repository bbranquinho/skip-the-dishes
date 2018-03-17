package br.com.skip.the.dishes.command.resource.order;

public class DeliveryAddressRequest {

    private String deliveryAddress;

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
