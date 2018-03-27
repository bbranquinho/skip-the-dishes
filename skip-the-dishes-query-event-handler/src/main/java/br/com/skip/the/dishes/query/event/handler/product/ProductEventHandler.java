package br.com.skip.the.dishes.query.event.handler.product;

import br.com.skip.the.dishes.domain.product.commons.ProductApplier;
import br.com.skip.the.dishes.domain.product.commons.ProductEvent;
import br.com.skip.the.dishes.domain.product.events.DetailUpdated;
import br.com.skip.the.dishes.domain.product.events.PriceUpdated;
import br.com.skip.the.dishes.domain.product.events.ProductCreated;
import br.com.skip.the.dishes.query.repository.product.ProductEntity;
import br.com.skip.the.dishes.query.repository.product.ProductRepository;
import br.com.zup.eventsourcing.core.*;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler implements EventHandler {

    private final ProductRepository productRepository;

    private final ProductApplier applier = new Applier();

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void handle(AggregateId aggregateId, Event event, MetaData metaData, AggregateVersion aggregateVersion) {
        ((ProductEvent)event).accept(aggregateId, applier);
    }

    private class Applier implements ProductApplier {
        @Override
        public void on(AggregateId aggregateId, ProductCreated productCreated) {
            ProductEntity productEntity = new ProductEntity(aggregateId.getValue(), productCreated.getName(),
                    productCreated.getDescription(), productCreated.getPrice(), productCreated.getStoreId());
            productRepository.save(productEntity);
        }

        @Override
        public void on(AggregateId aggregateId, PriceUpdated priceUpdated) {
            ProductEntity productEntity = productRepository.findById(aggregateId.getValue()).get();
            productEntity.setPrice(priceUpdated.getPrice());
            productRepository.save(productEntity);
        }

        @Override
        public void on(AggregateId aggregateId, DetailUpdated detailUpdated) {
            ProductEntity productEntity = productRepository.findById(aggregateId.getValue()).get();
            productEntity.setName(detailUpdated.getDetail().getName());
            productEntity.setDescription(detailUpdated.getDetail().getDescription());
            productRepository.save(productEntity);
        }
    }

}
