package br.com.skip.the.dishes.command.resource.product

import br.com.skip.the.dishes.command.resource.commons.Paths
import br.com.skip.the.dishes.domain.product.Detail
import br.com.skip.the.dishes.domain.product.commands.CreateProductCommand
import br.com.skip.the.dishes.domain.product.commands.ProductCommandHandler
import br.com.skip.the.dishes.domain.product.commands.UpdateDetailCommand
import br.com.skip.the.dishes.domain.product.commands.UpdatePriceCommand
import br.com.skip.the.dishes.domain.utils.DomainConstants.PRODUCT_ID_SIZE
import br.com.zup.eventsourcing.core.AggregateId
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping(path = [Paths.PRODUCT_PATH])
class ProductResource(private val productCommandHandler: ProductCommandHandler) : ProductApi {

    override fun create(@RequestBody @Valid createProductRequest: CreateProductRequest): CreateProductResponse {
        val command = CreateProductCommand(createProductRequest.name, createProductRequest.description,
                createProductRequest.storeId, createProductRequest.price)
        val product = productCommandHandler.handle(command)
        return CreateProductResponse(product.productId)
    }

    override fun updateDetail(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) id: String,
                              @RequestBody @Valid detailRequest: DetailRequest) {
        UpdateDetailCommand(AggregateId(id), Detail(detailRequest.name, detailRequest.description))
                .apply(productCommandHandler::handle)
    }

    override fun updatePrice(@PathVariable("id") @Valid @NotNull @Size(max = PRODUCT_ID_SIZE) id: String,
                             @RequestBody @Valid priceRequest: PriceRequest) {
        UpdatePriceCommand(AggregateId(id), priceRequest.price)
                .apply(productCommandHandler::handle)
    }

}
