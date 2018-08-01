package br.com.skip.the.dishes.query

import br.com.skip.the.dishes.query.event.handler.order.OrderSubscription
import br.com.skip.the.dishes.query.event.handler.product.ProductSubscription
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import javax.annotation.PostConstruct

@SpringBootApplication
@ComponentScan(basePackages = ["br.com.skip.the.dishes.query"])
@Import(SwaggerConfig::class)
@Configuration
class QueryConfig(val productSubscription: ProductSubscription, val orderSubscription: OrderSubscription) {

    @PostConstruct
    fun initSubscriptions() {
        productSubscription.start()
        orderSubscription.start()
    }

}

