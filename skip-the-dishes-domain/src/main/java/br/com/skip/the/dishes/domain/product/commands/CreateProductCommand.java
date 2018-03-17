package br.com.skip.the.dishes.domain.product.commands;

public class CreateProductCommand {

    private String name;

    private String description;

    private String storeId;

    private Double price;

    public CreateProductCommand(String name, String description, String storeId, Double price) {
        this.name = name;
        this.description = description;
        this.storeId = storeId;
        this.price = price;
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
