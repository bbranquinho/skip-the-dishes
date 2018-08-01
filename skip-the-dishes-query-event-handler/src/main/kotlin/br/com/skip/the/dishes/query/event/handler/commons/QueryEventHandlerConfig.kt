package br.com.skip.the.dishes.query.event.handler.commons

import feign.Feign
import feign.Logger
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@ComponentScan(basePackages = ["br.com.skip.the.dishes.query.event.handler"])
@Configuration
class QueryEventHandlerConfig(@Value("\${event.store.url}")
                              private val eventStoreUrl: String) {

    @Bean
    fun eventStoreApiService(eventStoreBasicAuth: EventStoreBasicAuth): EventStoreApiService =
            Feign.builder()
                    .client(OkHttpClient())
                    .requestInterceptor(eventStoreBasicAuth)
                    .logger(Slf4jLogger())
                    .logLevel(Logger.Level.FULL)
                    .target(EventStoreApiService::class.java, eventStoreUrl)

}
