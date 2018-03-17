package br.com.skip.the.dishes.command.resource.order;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.skip.the.dishes.command.resource.commons.Paths.ORDER_PATH;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping(path = ORDER_PATH)
public interface OrderApi {

    @ResponseStatus(CREATED)
    @PostMapping
    CreateResponse create(@RequestBody @Valid CreateRequest createRequest);

    @ResponseStatus(OK)
    @PostMapping(path = "/{id}/product")
    void addProduct(@PathVariable("id") String id, @RequestBody @Valid ProductRequest productRequest);

    @ResponseStatus(OK)
    @DeleteMapping(path = "/{id}/product")
    void deleteProduct(@PathVariable("id") String id, @RequestBody @Valid ProductRequest productRequest);

    @ResponseStatus(OK)
    @PostMapping(path = "/{id}/cancel")
    void cancelOrder(@PathVariable("id") String id);

    @ResponseStatus(OK)
    @PostMapping(path = "/{id}/request")
    void requestOrder(@PathVariable("id") String id);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/delivery_address")
    void updateDeliveryAddress(@PathVariable("id") String id, @RequestBody @Valid DeliveryAddressRequest deliveryAddressRequest);

}
