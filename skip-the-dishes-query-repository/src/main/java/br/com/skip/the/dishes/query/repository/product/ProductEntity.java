package br.com.skip.the.dishes.query.repository.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.domain.utils.DomainConstants.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @NotNull
    @Size(max = PRODUCT_ID_SIZE)
    private String id;

    @NotNull
    @Size(max = PRODUCT_NAME_SIZE)
    private String name;

    @NotNull
    @Size(max = PRODUCT_DESCRIPTION_SIZE)
    private String description;

    @NotNull
    @Min(PRODUCT_PRICE_MIN)
    private double price;

    @NotNull
    @Size(max = PRODUCT_STORE_ID_SIZE)
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
