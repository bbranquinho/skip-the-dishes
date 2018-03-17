package br.com.skip.the.dishes.query.event.handler.commons;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventStoreBasicAuth extends BasicAuthRequestInterceptor {

    public EventStoreBasicAuth(
            @Value("${event.store.user}") String user,
            @Value("${event.store.password}") String password) {
        super(user, password);
    }

}
