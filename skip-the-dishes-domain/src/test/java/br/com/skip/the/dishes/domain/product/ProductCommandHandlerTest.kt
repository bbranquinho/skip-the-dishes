package br.com.skip.the.dishes.domain.product

import br.com.skip.the.dishes.domain.product.commands.CreateProductCommand
import br.com.skip.the.dishes.domain.product.commands.ProductCommandHandler
import br.com.skip.the.dishes.domain.product.commands.UpdateDetailCommand
import br.com.skip.the.dishes.domain.product.commands.UpdatePriceCommand
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class ProductCommandHandlerTest {

    private val product = mock<Product> { }

    private val eventRepository = mock<EventStoreRepository<Product>> {
        on { get(any()) } doReturn product
    }

    private val productCommandHandle = ProductCommandHandler(eventRepository)

    private val productId = AggregateId("prod-1")

    @Test
    fun `Handle create product command`() {
        val createProductCommand = CreateProductCommand("Rice", "Delicious rice", "1", 10.12)

        productCommandHandle.handle(createProductCommand)

        verify(eventRepository).save(any())
    }

    @Test
    fun `Update details for a product`() {
        val detail = Detail("Bean", "Delicious bean")
        val updateDetailCommand = UpdateDetailCommand(productId, detail)

        productCommandHandle.handle(updateDetailCommand)

        verify(product).updateDetail(eq(detail))
        verify(eventRepository).get(eq(productId))
        verify(eventRepository).save(any())
    }

    @Test
    fun `Update price for a product`() {
        val updatePriceCommand = UpdatePriceCommand(productId, 20.10)

        productCommandHandle.handle(updatePriceCommand)

        verify(product).updatePrice(eq(20.10))
        verify(eventRepository).get(eq(productId))
        verify(eventRepository).save(any())
    }

}