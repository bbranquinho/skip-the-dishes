package br.com.skip.the.dishes.command.resource.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static br.com.skip.the.dishes.command.resource.commons.Paths.CUSTOMER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping(path = CUSTOMER_PATH)
public interface CustomerApi {

    @ResponseStatus(CREATED)
    @PostMapping
    CreateResponse create(@RequestBody @Valid CreateRequest createRequest);

}
