package br.com.skip.the.dishes.command.resource.order

import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import javax.validation.constraints.Size

import br.com.skip.the.dishes.command.resource.commons.Paths.ORDER_PATH
import br.com.skip.the.dishes.domain.utils.DomainConstants
import br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_ID_SIZE
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RequestMapping(path = [ORDER_PATH])
interface OrderApi {

    @ResponseStatus(CREATED)
    @PostMapping
    fun create(@RequestBody @Valid createRequest: CreateRequest): CreateResponse

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

data class CreateRequest(@field:[NotEmpty Size(max = DomainConstants.CUSTOMER_ID_SIZE)] val customerId: String)

data class CreateResponse(val orderId: String)

data class DeliveryAddressRequest(
        @field:[NotEmpty Size(max = DomainConstants.ORDER_DELIVERY_ADDRESS_SIZE)]
        val deliveryAddress: String,

        @field:[NotEmpty Size(max = DomainConstants.ORDER_CONTACT_SIZE)]
        val contact: String
)

data class ProductRequest(@field:[NotEmpty Size(max = DomainConstants.PRODUCT_ID_SIZE)] val productId: String)
