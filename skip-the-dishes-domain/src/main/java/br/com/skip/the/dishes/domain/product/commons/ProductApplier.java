package br.com.skip.the.dishes.domain.product.commons;

import br.com.skip.the.dishes.domain.product.events.DetailUpdated;
import br.com.skip.the.dishes.domain.product.events.PriceUpdated;
import br.com.skip.the.dishes.domain.product.events.ProductCreated;
import br.com.zup.eventsourcing.core.AggregateId;

public interface ProductApplier {

    void apply(AggregateId aggregateId, ProductCreated productCreated);

    void apply(AggregateId aggregateId, PriceUpdated priceUpdated);

    void apply(AggregateId aggregateId, DetailUpdated detailUpdated);

}
