package br.com.skip.the.dishes.domain.order;

public class DeliveryAddress {

    private String deliveryAddress;

    private String contact;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String deliveryAddress, String contact) {
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
