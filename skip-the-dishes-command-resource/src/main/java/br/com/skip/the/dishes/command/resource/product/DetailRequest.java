package br.com.skip.the.dishes.command.resource.product;

public class DetailRequest {

    private String name;

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
