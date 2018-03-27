package br.com.skip.the.dishes.domain.order

import br.com.skip.the.dishes.domain.order.events.DeliveryAddressUpdated
import br.com.skip.the.dishes.domain.order.events.OrderCancelled
import br.com.skip.the.dishes.domain.order.events.OrderCreated
import br.com.skip.the.dishes.domain.order.events.OrderRequested
import br.com.skip.the.dishes.domain.order.events.ProductAdded
import br.com.skip.the.dishes.domain.order.events.ProductDeleted
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class OrderEventTest {

    @Test
    fun `Apply OrderCreated with success`() {
        val order = Order()

        order.applyEvent(OrderCreated("Order ID", OrderStatus.NEW, "Customer ID"))

        assertEquals(expected = "Order ID", actual = order.orderId)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
        assertEquals(expected = "Customer ID", actual = order.customerId)

        assertTrue(order.products.isEmpty())

        assertNull(order.deliveryAddress)
    }

    @Test
    fun `Apply ProductAdded with success`() {
        val order = Order()

        order.applyEvent(OrderCreated("Order ID", OrderStatus.NEW, "Customer ID"))
        order.applyEvent(ProductAdded("Product ID 1"))

        assertEquals(expected = "Order ID", actual = order.orderId)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
        assertEquals(expected = "Customer ID", actual = order.customerId)
        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
    }

    @Test
    fun `Apply ProductDeleted with success`() {
        val order = Order()

        order.applyEvent(OrderCreated("Order ID", OrderStatus.NEW, "Customer ID"))
        order.applyEvent(ProductAdded("Product ID 1"))
        order.applyEvent(ProductAdded("Product ID 2"))

        assertEquals(expected = setOf("Product ID 1", "Product ID 2"), actual = order.products)

        order.applyEvent(ProductDeleted("Product ID 1"))

        assertEquals(expected = "Order ID", actual = order.orderId)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
        assertEquals(expected = "Customer ID", actual = order.customerId)
        assertEquals(expected = setOf("Product ID 2"), actual = order.products)
    }

    @Test
    fun `Apply OrderCancelled with success`() {
        val order = Order()

        order.applyEvent(OrderCreated("Order ID", OrderStatus.NEW, "Customer ID"))
        order.applyEvent(ProductAdded("Product ID 1"))
        order.applyEvent(OrderCancelled(OrderStatus.CANCELLED))

        assertEquals(expected = "Order ID", actual = order.orderId)
        assertEquals(expected = OrderStatus.CANCELLED, actual = order.status)
        assertEquals(expected = "Customer ID", actual = order.customerId)
        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
    }

    @Test
    fun `Apply OrderRequested with success`() {
        val order = Order()

        order.applyEvent(OrderCreated("Order ID", OrderStatus.NEW, "Customer ID"))
        order.applyEvent(ProductAdded("Product ID 1"))
        order.applyEvent(OrderRequested(OrderStatus.FINISHED))

        assertEquals(expected = "Order ID", actual = order.orderId)
        assertEquals(expected = OrderStatus.FINISHED, actual = order.status)
        assertEquals(expected = "Customer ID", actual = order.customerId)
        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
    }

    @Test
    fun `Apply DeliveryAddressUpdated with success`() {
        val order = Order()

        order.applyEvent(OrderCreated("Order ID", OrderStatus.NEW, "Customer ID"))
        order.applyEvent(ProductAdded("Product ID 1"))

        assertNull(order.deliveryAddress)

        order.applyEvent(DeliveryAddressUpdated(DeliveryAddress("Address 1", "Contact 1")))
        assertEquals(expected = DeliveryAddress("Address 1", "Contact 1"), actual = order.deliveryAddress)

        order.applyEvent(DeliveryAddressUpdated(DeliveryAddress("Address 2", "Contact 2")))
        assertEquals(expected = DeliveryAddress("Address 2", "Contact 2"), actual = order.deliveryAddress)

        assertEquals(expected = "Order ID", actual = order.orderId)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
        assertEquals(expected = "Customer ID", actual = order.customerId)
        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
    }

}
