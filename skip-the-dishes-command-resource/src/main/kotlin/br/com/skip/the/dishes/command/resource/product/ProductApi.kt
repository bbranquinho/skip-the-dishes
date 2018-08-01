package br.com.skip.the.dishes.command.resource.product

import org.springframework.web.bind.annotation.*

import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import br.com.skip.the.dishes.command.resource.commons.Paths.PRODUCT_PATH
import br.com.skip.the.dishes.domain.utils.DomainConstants
import br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_ID_SIZE
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@RequestMapping(path = [PRODUCT_PATH])
interface ProductApi {

    @ResponseStatus(CREATED)
    @PostMapping
    fun create(@RequestBody @Valid createRequest: CreateRequest): CreateResponse

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/detail"])
    fun updateDetail(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) id: String,
                     @RequestBody @Valid detailRequest: DetailRequest)

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/price"])
    fun updatePrice(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) id: String,
                    @RequestBody @Valid priceRequest: PriceRequest)

}

data class CreateRequest(
        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_NAME_SIZE)]
        val name: String,

        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_DESCRIPTION_SIZE)]
        val description: String,

        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_STORE_ID_SIZE)]
        val storeId: String,

        @field:[Min(DomainConstants.PRODUCT_PRICE_MIN.toLong())]
        val price: Double
)

data class CreateResponse(val productId: String)

data class DetailRequest(
        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_NAME_SIZE)]
        val name: String,

        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_DESCRIPTION_SIZE)]
        val description: String
)

data class PriceRequest(
        @field:[Min(DomainConstants.PRODUCT_PRICE_MIN.toLong())]
        val price: Double
)
