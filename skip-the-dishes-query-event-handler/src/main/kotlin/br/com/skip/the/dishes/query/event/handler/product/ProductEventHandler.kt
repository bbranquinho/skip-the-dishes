package br.com.skip.the.dishes.query.event.handler.product

import br.com.skip.the.dishes.domain.product.commons.ProductApplier
import br.com.skip.the.dishes.domain.product.commons.ProductEvent
import br.com.skip.the.dishes.domain.product.events.DetailUpdated
import br.com.skip.the.dishes.domain.product.events.PriceUpdated
import br.com.skip.the.dishes.domain.product.events.ProductCreated
import br.com.skip.the.dishes.query.repository.product.ProductEntity
import br.com.skip.the.dishes.query.repository.product.ProductRepository
import br.com.zup.eventsourcing.core.*
import org.springframework.stereotype.Component

@Component
class ProductEventHandler(private val productRepository: ProductRepository) : EventHandler {

    private val applier = Applier()

    override fun handle(aggregateId: AggregateId, event: Event, metaData: MetaData, aggregateVersion: AggregateVersion) {
        (event as ProductEvent).accept(aggregateId, applier)
    }

    private inner class Applier : ProductApplier {
        override fun on(aggregateId: AggregateId, productCreated: ProductCreated) {
            ProductEntity(
                    id = aggregateId.value,
                    name = productCreated.name,
                    description = productCreated.description,
                    price = productCreated.price,
                    storeId = productCreated.storeId
            ).apply { productRepository.save(this) }
        }

        override fun on(aggregateId: AggregateId, priceUpdated: PriceUpdated) {
            productRepository.findById(aggregateId.value).get()
                    .copy(price = priceUpdated.price)
                    .apply { productRepository.save(this) }
        }

        override fun on(aggregateId: AggregateId, detailUpdated: DetailUpdated) {
            productRepository
                    .findById(aggregateId.value)
                    .get()
                    .copy(name = detailUpdated.detail.name, description = detailUpdated.detail.description)
                    .apply { productRepository.save(this) }
        }
    }

}
