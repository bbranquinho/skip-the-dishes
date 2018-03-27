package br.com.skip.the.dishes.domain.product

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ProductTest {

    @Test
    fun `Create a product with success`() {
        val product = Product("Name", "Description", "Store ID", 12.53)

        assertNotNull(product.productId)

        assertEquals(expected = Detail("Name", "Description"), actual = product.detail)
        assertEquals(expected = "Store ID", actual = product.storeId)
        assertEquals(expected = 12.53, actual = product.price)
    }

    @Test
    fun `Update price with success`() {
        val product = Product("Name", "Description", "Store ID", 12.53)

        product.updatePrice(20.0)

        assertEquals(expected = 20.0, actual = product.price)
    }

    @Test
    fun `Update details with success`() {
        val product = Product("Name", "Description", "Store ID", 12.53)

        product.updateDetail(Detail("Name 2", "Description 2"))

        assertEquals(expected = Detail("Name 2", "Description 2"), actual = product.detail)
    }

}
