package br.com.skip.the.dishes.command.resource.product;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.command.resource.commons.Paths.PRODUCT_PATH;
import static br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_ID_SIZE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping(path = PRODUCT_PATH)
public interface ProductApi {

    @ResponseStatus(CREATED)
    @PostMapping
    CreateResponse create(@RequestBody @Valid CreateRequest createRequest);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/detail")
    void updateDetail(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) String id,
                      @RequestBody @Valid DetailRequest detailRequest);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/price")
    void updatePrice(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) String id,
                     @RequestBody @Valid PriceRequest priceRequest);

}
