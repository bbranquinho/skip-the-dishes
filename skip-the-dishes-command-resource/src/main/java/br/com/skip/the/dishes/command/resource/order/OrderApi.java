package br.com.skip.the.dishes.command.resource.order;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import static br.com.skip.the.dishes.command.resource.commons.Paths.ORDER_PATH;
import static br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_ID_SIZE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping(path = ORDER_PATH)
public interface OrderApi {

    @ResponseStatus(CREATED)
    @PostMapping
    CreateResponse create(@RequestBody @Valid CreateRequest createRequest);

    @ResponseStatus(OK)
    @PostMapping(path = "/{id}/product")
    void addProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id,
                    @RequestBody @Valid ProductRequest productRequest);

    @ResponseStatus(OK)
    @DeleteMapping(path = "/{id}/product")
    void deleteProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id,
                       @RequestBody @Valid ProductRequest productRequest);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/cancel")
    void cancelOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/request")
    void requestOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id);

    @ResponseStatus(OK)
    @PatchMapping(path = "/{id}/delivery_address")
    void updateDeliveryAddress(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) String id,
                               @RequestBody @Valid DeliveryAddressRequest deliveryAddressRequest);

}
