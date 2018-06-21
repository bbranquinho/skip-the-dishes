package br.com.skip.the.dishes.command.repository

import br.com.skip.the.dishes.domain.product.Product
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.springframework.stereotype.Service

@Service
class ProductRepository : EventStoreRepository<Product>()
