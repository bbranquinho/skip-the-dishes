package br.com.skip.the.dishes.query.event.handler.customer;

import br.com.zup.eventsourcing.core.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventHandler implements EventHandler {

    @Override
    public void handle(AggregateId aggregateId, Event event, MetaData metaData, AggregateVersion aggregateVersion) {

    }

}
