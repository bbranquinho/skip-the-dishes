package br.com.skip.the.dishes.command.resource

import br.com.skip.the.dishes.domain.order.Order
import br.com.skip.the.dishes.domain.order.commands.CreateOrderCommand
import br.com.skip.the.dishes.domain.order.commands.OrderCommandHandler
import br.com.skip.the.dishes.domain.product.Product
import br.com.skip.the.dishes.domain.product.commands.CreateProductCommand
import br.com.skip.the.dishes.domain.product.commands.ProductCommandHandler
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@EnableAutoConfiguration
@ComponentScan(basePackages = ["br.com.skip.the.dishes.command.resource"])
@Configuration
class CommandResourceConfigTest {

    private val product = Product("Rice", "Delicious rice", "StoreId", 10.0)

    private val productCommandHandler = mock<ProductCommandHandler> {
        on { handle(any<CreateProductCommand>()) } doReturn product
    }

    private val order = Order("user@skip.ca")

    private val orderCommandHandler = mock<OrderCommandHandler> {
        on { handle(any<CreateOrderCommand>()) } doReturn order
    }

    @Bean
    fun productCommandHandler(): ProductCommandHandler =
            productCommandHandler

    @Bean
    fun orderCommandHandler(): OrderCommandHandler =
            orderCommandHandler

}
