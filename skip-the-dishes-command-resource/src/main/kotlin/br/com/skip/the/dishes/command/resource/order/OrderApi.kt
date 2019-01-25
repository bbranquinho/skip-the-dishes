package br.com.skip.the.dishes.command.resource.order

import br.com.skip.the.dishes.command.resource.commons.Paths.ORDER_PATH
import br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_ID_SIZE
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Size

@RequestMapping(path = [ORDER_PATH])
interface OrderApi {

    @ResponseStatus(CREATED)
    @PostMapping
    fun create(@RequestBody @Valid createOrderRequest: CreateOrderRequest): CreateOrderResponse

    @ResponseStatus(OK)
    @PostMapping(path = ["/{id}/product"])
    fun addProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String,
                   @RequestBody @Valid productRequest: ProductRequest)

    @ResponseStatus(OK)
    @DeleteMapping(path = ["/{id}/product"])
    fun deleteProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String,
                      @RequestBody @Valid productRequest: ProductRequest)

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/cancel"])
    fun cancelOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String)

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/request"])
    fun requestOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String)

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/delivery_address"])
    fun updateDeliveryAddress(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String,
                              @RequestBody @Valid deliveryAddressRequest: DeliveryAddressRequest)

}
