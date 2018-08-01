package br.com.skip.the.dishes.query.event.handler.commons

import feign.auth.BasicAuthRequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EventStoreBasicAuth(
        @Value("\${event.store.user}") user: String,
        @Value("\${event.store.password}") password: String
) : BasicAuthRequestInterceptor(user, password)
