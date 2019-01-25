package br.com.skip.the.dishes.command.resource.product

import br.com.skip.the.dishes.domain.utils.DomainConstants
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CreateProductRequest(
        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_NAME_SIZE)]
        val name: String,

        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_DESCRIPTION_SIZE)]
        val description: String,

        @field:[NotEmpty Size(max = DomainConstants.PRODUCT_STORE_ID_SIZE)]
        val storeId: String,

        @field:[Min(DomainConstants.PRODUCT_PRICE_MIN.toLong())]
        val price: Double
)

data class CreateProductResponse(val productId: String)

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
