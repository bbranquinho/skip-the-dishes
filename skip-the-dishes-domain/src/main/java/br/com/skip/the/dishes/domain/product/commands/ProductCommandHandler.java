package br.com.skip.the.dishes.domain.product.commands;

import br.com.skip.the.dishes.domain.product.Product;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProductCommandHandler.class);

    private final EventStoreRepository<Product> eventRepository;

    public ProductCommandHandler(EventStoreRepository<Product> eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Product handle(CreateProductCommand createProductCommand) {
        Product product = new Product(createProductCommand.getName(), createProductCommand.getDescription(),
                createProductCommand.getStoreId(), createProductCommand.getPrice());
        eventRepository.save(product);
        logger.debug("Product [{}] created.", product.getProductId());
        return product;
    }

    public void handle(UpdateDetailCommand updateDetailCommand) {
        Product product = eventRepository.get(updateDetailCommand.getProductId());
        product.updateDetail(updateDetailCommand.getDetail());
        eventRepository.save(product);
    }

    public void handle(UpdatePriceCommand updatePriceCommand) {
        Product product = eventRepository.get(updatePriceCommand.getProductId());
        product.updatePrice(updatePriceCommand.getPrice());
        eventRepository.save(product);
    }

}
