package br.com.skip.the.dishes.query.event.handler.product;

import br.com.skip.the.dishes.domain.product.Product;
import br.com.zup.eventsourcing.eventstore.PersistentAggregateSubscriber;
import org.springframework.stereotype.Component;

@Component
public class ProductSubscription extends PersistentAggregateSubscriber<Product> {

    public ProductSubscription(ProductEventHandler productEventHandler) {
        super("Product", productEventHandler);
    }

}
