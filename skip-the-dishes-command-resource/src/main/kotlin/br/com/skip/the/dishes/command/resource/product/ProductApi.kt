package br.com.skip.the.dishes.command.resource.product

import br.com.skip.the.dishes.command.resource.commons.Paths.PRODUCT_PATH
import br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_ID_SIZE
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RequestMapping(path = [PRODUCT_PATH])
interface ProductApi {

    @ResponseStatus(CREATED)
    @PostMapping
    fun create(@RequestBody @Valid createProductRequest: CreateProductRequest): CreateProductResponse

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/detail"])
    fun updateDetail(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) id: String,
                     @RequestBody @Valid detailRequest: DetailRequest)

    @ResponseStatus(OK)
    @PatchMapping(path = ["/{id}/price"])
    fun updatePrice(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) id: String,
                    @RequestBody @Valid priceRequest: PriceRequest)

}
