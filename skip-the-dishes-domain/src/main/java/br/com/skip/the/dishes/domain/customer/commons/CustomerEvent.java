package br.com.skip.the.dishes.domain.customer.commons;

import br.com.zup.eventsourcing.core.AggregateId;

public interface CustomerEvent {

    void accept(AggregateId aggregateId, CustomerApplier customerApplier);

}
