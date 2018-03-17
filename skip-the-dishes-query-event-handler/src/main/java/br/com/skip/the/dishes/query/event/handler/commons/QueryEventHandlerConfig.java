package br.com.skip.the.dishes.query.event.handler.commons;

import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "br.com.skip.the.dishes.query.event.handler")
@Configuration
public class QueryEventHandlerConfig {

    @Value("${event.store.url}")
    private String eventStoreUrl;

    @Bean
    public EventStoreApiService eventStoreApiService(EventStoreBasicAuth eventStoreBasicAuth) {
        return Feign.builder()
                .client(new OkHttpClient())
                .requestInterceptor(eventStoreBasicAuth)
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(EventStoreApiService.class, eventStoreUrl);
    }

}
