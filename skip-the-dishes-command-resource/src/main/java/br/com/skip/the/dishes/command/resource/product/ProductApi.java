package br.com.skip.the.dishes.command.resource.product;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.skip.the.dishes.command.resource.commons.Paths.PRODUCT_PATH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping(path = PRODUCT_PATH)
public interface ProductApi {

    @ResponseStatus(CREATED)
    @PostMapping
    CreateResponse create(@RequestBody @Valid CreateRequest createRequest);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/detail")
    void updateDetail(@PathVariable("id") String id, @RequestBody @Valid DetailRequest detailRequest);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/price")
    void updatePrice(@PathVariable("id") String id, @RequestBody @Valid PriceRequest priceRequest);

}
