package br.com.skip.the.dishes.domain.product.commands;

import br.com.zup.eventsourcing.core.AggregateId;

public class UpdatePriceCommand {

    private AggregateId productId;

    private double price;

    public UpdatePriceCommand(AggregateId productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    public AggregateId getProductId() {
        return productId;
    }

    public void setProductId(AggregateId productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
