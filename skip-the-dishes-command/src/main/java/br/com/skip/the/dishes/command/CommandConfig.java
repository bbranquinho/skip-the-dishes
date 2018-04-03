package br.com.skip.the.dishes.command;

import br.com.skip.the.dishes.domain.order.Order;
import br.com.skip.the.dishes.domain.order.commands.OrderCommandHandler;
import br.com.skip.the.dishes.domain.product.Product;
import br.com.skip.the.dishes.domain.product.commands.ProductCommandHandler;
import br.com.zup.eventsourcing.eventstore.EventStoreRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@ComponentScan(basePackages = {"br.com.skip.the.dishes.command"})
@EnableResourceServer
@Configuration
public class CommandConfig {

    @Bean
    public OrderCommandHandler orderCommandHandler(EventStoreRepository<Order> orderEventRepository,
                                                   EventStoreRepository<Product> productEventRepository) {
        return new OrderCommandHandler(orderEventRepository, productEventRepository);
    }

    @Bean
    public ProductCommandHandler productCommandHandler(EventStoreRepository<Product> productEventRepository) {
        return new ProductCommandHandler(productEventRepository);
    }

}

