package br.com.skip.the.dishes.domain.order;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryAddress that = (DeliveryAddress) o;
        return Objects.equals(deliveryAddress, that.deliveryAddress) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deliveryAddress, contact);
    }
}
