package br.com.skip.the.dishes.domain.order

import br.com.skip.the.dishes.domain.order.commands.AddProductCommand
import br.com.skip.the.dishes.domain.order.commands.CancelOrderCommand
import br.com.skip.the.dishes.domain.order.commands.CreateOrderCommand
import br.com.skip.the.dishes.domain.order.commands.DeleteProductCommand
import br.com.skip.the.dishes.domain.order.commands.OrderCommandHandler
import br.com.skip.the.dishes.domain.order.commands.RequestOrderCommand
import br.com.skip.the.dishes.domain.order.commands.UpdateDeliveryAddressCommand
import br.com.skip.the.dishes.domain.product.Product
import br.com.zup.eventsourcing.core.AggregateId
import br.com.zup.eventsourcing.core.Repository
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test
import org.mockito.internal.verification.Times

class OrderCommandHandlerTest {

    private val order = mock<Order> { }

    private val orderEventRepository = mock<EventStoreRepository<Order>> {
        on { get(AggregateId("Order ID")) } doReturn order
        on { get(AggregateId("Order ID not found")) } doAnswer { throw Repository.NotFoundException() }
    }

    private val product = mock<Product> { }

    private val productEventRepository = mock<EventStoreRepository<Product>> {
        on { get(AggregateId("Product ID")) } doReturn product
    }

    private val orderCommandHandler = OrderCommandHandler(orderEventRepository, productEventRepository)

    @Test
    fun `Handle the command CreateOrderCommand with successs`() {
        val command = CreateOrderCommand(customerId = "Customer ID")

        orderCommandHandler.handle(command)

        verify(orderEventRepository, Times(1)).save(argThat { orderId != null })
    }

    @Test
    fun `Handle the command AddProductCommand with successs`() {
        val command = AddProductCommand(orderId = "Order ID", productId = "Product ID")

        orderCommandHandler.handle(command)

        verify(order, Times(1)).addProduct(eq("Product ID"), eq(productEventRepository))
        verify(orderEventRepository, Times(1)).get(eq(AggregateId("Order ID")))
        verify(orderEventRepository, Times(1)).save(any())
    }

    @Test(expected = Repository.NotFoundException::class)
    fun `Try to handle the command AddProductCommand, but Order is not found and should throw a NotFoundException`() {
        val command = AddProductCommand(orderId = "Order ID not found", productId = "Product ID")

        orderCommandHandler.handle(command)
    }

    @Test
    fun `Handle the command DeleteProductCommand with successs`() {
        val command = DeleteProductCommand(orderId = "Order ID", productId = "Product ID")

        orderCommandHandler.handle(command)

        verify(order, Times(1)).deleteProduct(eq("Product ID"))
        verify(orderEventRepository, Times(1)).get(eq(AggregateId("Order ID")))
        verify(orderEventRepository, Times(1)).save(any())
    }

    @Test
    fun `Handle the command CancelOrderCommand with successs`() {
        val command = CancelOrderCommand(orderId = "Order ID")

        orderCommandHandler.handle(command)

        verify(order, Times(1)).cancelOrder()
        verify(orderEventRepository, Times(1)).get(eq(AggregateId("Order ID")))
        verify(orderEventRepository, Times(1)).save(any())
    }

    @Test
    fun `Handle the command RequestOrderCommand with successs`() {
        val command = RequestOrderCommand(orderId = "Order ID")

        orderCommandHandler.handle(command)

        verify(order, Times(1)).requestOrder()
        verify(orderEventRepository, Times(1)).get(eq(AggregateId("Order ID")))
        verify(orderEventRepository, Times(1)).save(any())
    }

    @Test
    fun `Handle the command UpdateDeliveryAddressCommand with successs`() {
        val command = UpdateDeliveryAddressCommand(
                orderId = "Order ID",
                deliveryAddress = DeliveryAddress("Address", "Contact")
        )

        orderCommandHandler.handle(command)

        verify(order, Times(1)).updateDeliveryAddress(eq(DeliveryAddress("Address", "Contact")))
        verify(orderEventRepository, Times(1)).get(eq(AggregateId("Order ID")))
        verify(orderEventRepository, Times(1)).save(any())
    }

}
