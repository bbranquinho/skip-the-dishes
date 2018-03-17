package br.com.skip.the.dishes.domain.product.commons;

import br.com.zup.eventsourcing.core.AggregateId;

public interface ProductEvent {

    void accept(AggregateId aggregateId, ProductApplier productApplier);

}
