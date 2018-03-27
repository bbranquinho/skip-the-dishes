package br.com.skip.the.dishes.domain.order

import br.com.skip.the.dishes.domain.assertException
import br.com.skip.the.dishes.domain.order.commons.AttemptChangeOrderStatusException
import br.com.skip.the.dishes.domain.product.Product
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Repository
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.mockito.internal.verification.Times
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class OrderTest {

    private val product = Product()

    private val productEventRepository = mock<EventStoreRepository<Product>> {
        on { get(AggregateId("Product ID 1")) } doReturn product
        on { get(AggregateId("Product ID 2")) } doReturn product
        on { get(AggregateId("Product ID 3")) } doAnswer { throw Repository.NotFoundException() }
    }

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

        order.addProduct("Product ID 1", productEventRepository)
        order.addProduct("Product ID 2", productEventRepository)

        assertEquals(expected = setOf("Product ID 1", "Product ID 2"), actual = order.products)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)

        verify(productEventRepository, Times(1)).get(AggregateId("Product ID 1"))
        verify(productEventRepository, Times(1)).get(AggregateId("Product ID 2"))
    }

    @Test(expected = Repository.NotFoundException::class)
    fun `Try to add a product that not exists, it should throw a NotFoundException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)
        order.addProduct("Product ID 2", productEventRepository)
        order.addProduct("Product ID 3", productEventRepository)
    }

    @Test
    fun `Add and delete products with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)
        order.addProduct("Product ID 2", productEventRepository)

        order.deleteProduct("Product ID 2")

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
    }

    @Test
    fun `Cancel a order with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)

        order.cancelOrder()

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = OrderStatus.CANCELLED, actual = order.status)
    }

    @Test
    fun `Request a order with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)

        order.requestOrder()

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = OrderStatus.FINISHED, actual = order.status)
    }

    @Test
    fun `Update DeliveryAddress with success`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)

        order.updateDeliveryAddress(DeliveryAddress("Address 1", "Contact 1"))

        assertEquals(expected = setOf("Product ID 1"), actual = order.products)
        assertEquals(expected = DeliveryAddress("Address 1", "Contact 1"), actual = order.deliveryAddress)
        assertEquals(expected = OrderStatus.NEW, actual = order.status)
    }

    @Test
    fun `Try to add a product to an order already cancelled, it should throw an AttemptChangeOrderStatusException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)
        order.cancelOrder()

        assertException(
                expectedException = AttemptChangeOrderStatusException::class.java,
                expectedMessage = "It is not allowed change order with status Cancelled"
        ) {
            order.addProduct("Product ID 2", productEventRepository)
        }
    }

    @Test
    fun `Try to delete a product to an order already finished, it should throw an AttemptChangeOrderStatusException`() {
        val order = Order("Customer ID")

        order.addProduct("Product ID 1", productEventRepository)
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

        order.addProduct("Product ID 1", productEventRepository)
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

        order.addProduct("Product ID 1", productEventRepository)
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