package br.com.skip.the.dishes.query.resource

import br.com.skip.the.dishes.query.repository.order.OrderEntity
import br.com.skip.the.dishes.query.repository.order.OrderRepository
import br.com.skip.the.dishes.query.repository.product.ProductEntity
import br.com.skip.the.dishes.query.repository.product.ProductRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.Optional

@EnableAutoConfiguration
@ComponentScan(basePackages = ["br.com.skip.the.dishes.query.resource"])
@Configuration
class QueryResourceConfigTest {

    val product = ProductEntity("1", "Name 1", "Description 1", 11.42, "Store Id 1")

    val products = listOf(
            ProductEntity("1", "Name 1", "Description 1", 11.42, "Store Id 1"),
            ProductEntity("2", "Name 2", "Description 2", 10.42, "Store Id 2"),
            ProductEntity("3", "Name 3", "Description 3", 9.42, "Store Id 3"),
            ProductEntity("4", "Name 4", "Description 4", 8.42, "Store Id 4"),
            ProductEntity("5", "Name 5", "Description 5", 7.42, "Store Id 5"),
            ProductEntity("6", "Name 6", "Description 6", 6.42, "Store Id 6")
    )

    val productRepository = mock<ProductRepository> {
        on { findAll(PageRequest.of(0, 3)) } doReturn
                PageImpl<ProductEntity>(products.take(3), PageRequest.of(0, 3), products.size.toLong())
        on { findAll(PageRequest.of(0, 20)) } doReturn
                PageImpl<ProductEntity>(products, PageRequest.of(0, 20), products.size.toLong())
        on { findById("24ee7c24-2a06-11e8-b467-0ed5f89f718b") } doReturn Optional.of(product)
    }

    val order = OrderEntity("1", "Customer ID 1", "Address 1", "5534999998888", "NEW")

    val orders = listOf(
            OrderEntity("1", "Customer ID 1", "Address 1", "5534999998881", "NEW"),
            OrderEntity("2", "Customer ID 2", "Address 2", "5534999998882", "FINISHED"),
            OrderEntity("3", "Customer ID 3", "Address 3", "5534999998883", "FINISHED"),
            OrderEntity("4", "Customer ID 4", "Address 4", "5534999998884", "NEW"),
            OrderEntity("5", "Customer ID 5", "Address 5", "5534999998885", "CANCELLED"),
            OrderEntity("6", "Customer ID 6", "Address 6", "5534999998886", "NEW")
    )

    val orderRepository = mock<OrderRepository> {
        on { findAll(PageRequest.of(0, 3)) } doReturn
                PageImpl<OrderEntity>(orders.take(3), PageRequest.of(0, 3), orders.size.toLong())
        on { findAll(PageRequest.of(0, 20)) } doReturn
                PageImpl<OrderEntity>(orders, PageRequest.of(0, 20), orders.size.toLong())
        on { findById("24ee7c24-2a06-11e8-b467-0ed5f89f718b") } doReturn Optional.of(order)
    }

    @Bean
    @Primary
    fun productRepository(): ProductRepository =
            productRepository

    @Bean
    @Primary
    fun orderRepository(): OrderRepository =
            orderRepository

}
