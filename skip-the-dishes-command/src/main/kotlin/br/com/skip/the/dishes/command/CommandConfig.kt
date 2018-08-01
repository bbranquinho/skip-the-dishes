package br.com.skip.the.dishes.command

import br.com.skip.the.dishes.domain.order.Order
import br.com.skip.the.dishes.domain.order.elements.CommandHandler
import br.com.skip.the.dishes.domain.product.Product
import br.com.skip.the.dishes.domain.product.commands.ProductCommandHandler
import br.com.zup.eventsourcing.eventstore.EventStoreRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
@ComponentScan(basePackages = ["br.com.skip.the.dishes.command"])
@Import(SwaggerConfig::class)
@Configuration
class CommandConfig {

    @Bean
    fun orderCommandHandler(orderEventRepository: EventStoreRepository<Order>,
                            productEventRepository: EventStoreRepository<Product>): CommandHandler {
        return CommandHandler(orderEventRepository, productEventRepository)
    }

    @Bean
    fun productCommandHandler(productEventRepository: EventStoreRepository<Product>): ProductCommandHandler {
        return ProductCommandHandler(productEventRepository)
    }

}
