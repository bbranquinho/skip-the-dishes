package br.com.skip.the.dishes.domain.product;

import br.com.skip.the.dishes.domain.product.commons.ProductApplier;
import br.com.skip.the.dishes.domain.product.commons.ProductEvent;
import br.com.skip.the.dishes.domain.product.events.DetailUpdated;
import br.com.skip.the.dishes.domain.product.events.PriceUpdated;
import br.com.skip.the.dishes.domain.product.events.ProductCreated;
import br.com.zup.eventsourcing.core.AggregateId;
import br.com.zup.eventsourcing.core.AggregateRoot;
import br.com.zup.eventsourcing.core.Event;

import java.util.UUID;

public class Product extends AggregateRoot {

    private final Applier applier = new Applier();

    private Detail detail;

    private String storeId;

    private Double price;

    public Product() {
    }

    public Product(String name, String description, String storeId, Double price) {
        ProductCreated productCreatedEvent = new ProductCreated(UUID.randomUUID().toString(), name, description, storeId, price);
        applyChange(productCreatedEvent);
    }

    @Override
    public void applyEvent(Event event) {
        ((ProductEvent)event).accept(id, applier);
    }

    public void updateDetail(Detail detail) {
        DetailUpdated detailUpdatedEvent = new DetailUpdated(detail);
        applyChange(detailUpdatedEvent);
    }

    public void updatePrice(double newPrice) {
        PriceUpdated priceUpdatedEvent = new PriceUpdated(newPrice);
        applyChange(priceUpdatedEvent);
    }

    private class Applier implements ProductApplier {

        @Override
        public void on(AggregateId aggregateId, ProductCreated productCreated) {
            id = new AggregateId(productCreated.getProductId());
            detail = new Detail(productCreated.getName(), productCreated.getDescription());
            storeId = productCreated.getStoreId();
            price = productCreated.getPrice();
        }

        @Override
        public void on(AggregateId aggregateId, PriceUpdated priceUpdated) {
            price = priceUpdated.getPrice();
        }

        @Override
        public void on(AggregateId aggregateId, DetailUpdated detailUpdated) {
            detail = detailUpdated.getDetail();
        }

    }

    public String getProductId() {
        return id.getValue();
    }

    public Detail getDetail() {
        return detail;
    }

    public String getStoreId() {
        return storeId;
    }

    public Double getPrice() {
        return price;
    }
}
