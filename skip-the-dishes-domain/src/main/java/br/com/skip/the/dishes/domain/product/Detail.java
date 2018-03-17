package br.com.skip.the.dishes.domain.product;

public class Detail {

    private final String name;

    private final String description;

    public Detail(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
