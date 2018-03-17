package br.com.skip.the.dishes.query.repository.product;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    private String id;

    private String name;

    private String description;

    private double price;

    @Column(name = "store_id")
    private String storeId;

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, String description, double price, String storeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.storeId = storeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
