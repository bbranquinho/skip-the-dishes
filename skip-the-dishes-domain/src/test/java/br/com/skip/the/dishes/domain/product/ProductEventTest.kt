package br.com.skip.the.dishes.domain.product

import br.com.skip.the.dishes.domain.product.events.DetailUpdated
import br.com.skip.the.dishes.domain.product.events.PriceUpdated
import br.com.skip.the.dishes.domain.product.events.ProductCreated
import org.junit.Test
import kotlin.test.assertEquals

class ProductEventTest {

    @Test
    fun `Apply ProductCreated event with success`() {
        val product = Product()

        product.applyEvent(ProductCreated("Product ID", "Name", "Description", "Store ID", 12.53))

        assertEquals(expected = "Product ID", actual = product.productId)
        assertEquals(expected = Detail("Name", "Description"), actual = product.detail)
        assertEquals(expected = "Store ID", actual = product.storeId)
        assertEquals(expected = 12.53, actual = product.price)
    }

    @Test
    fun `Apply PriceUpdated event with success`() {
        val product = Product()

        product.applyEvent(ProductCreated("Product ID", "Name", "Description", "Store ID", 12.53))
        product.applyEvent(PriceUpdated(20.36))

        assertEquals(expected = "Product ID", actual = product.productId)
        assertEquals(expected = Detail("Name", "Description"), actual = product.detail)
        assertEquals(expected = "Store ID", actual = product.storeId)
        assertEquals(expected = 20.36, actual = product.price)
    }

    @Test
    fun `Apply DetailUpdated event with success`() {
        val product = Product()

        product.applyEvent(ProductCreated("Product ID", "Name", "Description", "Store ID", 12.53))
        product.applyEvent(DetailUpdated(Detail("Name 1", "Description 1")))

        assertEquals(expected = "Product ID", actual = product.productId)
        assertEquals(expected = Detail("Name 1", "Description 1"), actual = product.detail)
        assertEquals(expected = "Store ID", actual = product.storeId)
        assertEquals(expected = 12.53, actual = product.price)
    }

}