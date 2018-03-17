package br.com.skip.the.dishes.query.repository.order;

import br.com.skip.the.dishes.query.repository.product.ProductEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    private String contact;

    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_ir"),
            inverseJoinColumns = @JoinColumn(name = "product_pk")
    )
    private List<ProductEntity> products;

    public OrderEntity() {
    }

    public OrderEntity(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public OrderEntity(String id, String customerId, String deliveryAddress, String contact, String status) {
        this.id = id;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
