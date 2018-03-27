package br.com.skip.the.dishes.domain.product;

import java.util.Objects;

public class Detail {

    private String name;

    private String description;

    private Detail() {
    }

    public Detail(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return Objects.equals(name, detail.name) &&
                Objects.equals(description, detail.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

}
