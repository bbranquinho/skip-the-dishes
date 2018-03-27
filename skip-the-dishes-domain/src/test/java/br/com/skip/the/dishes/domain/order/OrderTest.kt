package br.com.skip.the.dishes.domain.order

import br.com.skip.the.dishes.domain.assertException
import br.com.skip.the.dishes.domain.order.commons.AttemptChangeOrderStatusException
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class OrderTest {

    @Test
    fun `Create an order with success`() {
        val order = Order("Customer ID")

        assertNotNull(order.orderId)

        assertEquals(expected = "Customer ID", actual = order.customerId)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
    }

    @Test
    fun `Add two products with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")
        order.addProduct("Product ID 2")

        assertEquals(expected = setOf("Product ID 1", "Product ID 2"), actual = order.products)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
    }

    @Test
    fun `Add and delete products with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")
        order.addProduct("Product ID 2")

        order.deleteProduct("Product ID 2")

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
    }

    @Test
    fun `Cancel a order with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")

        order.cancelOrder()

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = OrderStatus.CANCELLED, actual = order.status)
    }

    @Test
    fun `Request a order with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")

        order.requestOrder()

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = OrderStatus.FINISHED, actual = order.status)
    }

    @Test
    fun `Update DeliveryAddress with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")

        order.updateDeliveryAddress(DeliveryAddress("Address 1", "Contact 1"))

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = DeliveryAddress("Address 1", "Contact 1"), actual = order.deliveryAddress)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
    }

    @Test
    fun `Try to add a product to an order already cancelled, it should throw an AttemptChangeOrderStatusException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")
        order.cancelOrder()

        assertException(
                expectedException = AttemptChangeOrderStatusException::class.java,
                expectedMessage = "It is not allowed change order with status Cancelled"
        ) {
            order.addProduct("Product ID 2")
        }
    }

    @Test
    fun `Try to delete a product to an order already finished, it should throw an AttemptChangeOrderStatusException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")
        order.requestOrder()

        assertException(
                expectedException = AttemptChangeOrderStatusException::class.java,
                expectedMessage = "It is not allowed change order with status Finished"
        ) {
            order.deleteProduct("Product ID 1")
        }
    }

    @Test
    fun `Try to request an order already cancelled, it should throw an AttemptChangeOrderStatusException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")
        order.cancelOrder()

        assertException(
                expectedException = AttemptChangeOrderStatusException::class.java,
                expectedMessage = "It is not allowed change order with status Cancelled"
        ) {
            order.requestOrder()
        }
    }

    @Test
    fun `Try to update the delivery address for an order already cancelled, it should throw an AttemptChangeOrderStatusException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1")
        order.updateDeliveryAddress(DeliveryAddress("Address 1", "Contact 1"))

        order.cancelOrder()

        assertException(
                expectedException = AttemptChangeOrderStatusException::class.java,
                expectedMessage = "It is not allowed change order with status Cancelled"
        ) {
            order.updateDeliveryAddress(DeliveryAddress("Address 2", "Contact 2"))
        }
    }

}