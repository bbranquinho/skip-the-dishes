package br.com.skip.the.dishes.query.event.handler.commons;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface EventStoreApiService {

    @RequestLine("POST /projection/{name}/command/enable")
    void enableProjection(@Param("name") String name);

}
