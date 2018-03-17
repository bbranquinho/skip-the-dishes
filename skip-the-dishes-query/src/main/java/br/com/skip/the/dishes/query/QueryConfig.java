package br.com.skip.the.dishes.query;

import br.com.skip.the.dishes.query.event.handler.product.ProductEventHandler;
import br.com.skip.the.dishes.query.event.handler.product.ProductSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.skip.the.dishes.query")
@Configuration
public class QueryConfig {

    @Autowired
    private ProductSubscription productSubscription;

    @PostConstruct
    public void initSubscriptions() {
        productSubscription.start();
    }

}

