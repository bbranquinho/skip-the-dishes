package br.com.skip.the.dishes.command.resource.order

import br.com.skip.the.dishes.domain.utils.DomainConstants
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CreateOrderRequest(@field:[NotEmpty Size(max = DomainConstants.CUSTOMER_ID_SIZE)] val customerId: String)

data class CreateOrderResponse(val orderId: String)

data class DeliveryAddressRequest(
        @field:[NotEmpty Size(max = DomainConstants.ORDER_DELIVERY_ADDRESS_SIZE)]
        val deliveryAddress: String,

        @field:[NotEmpty Size(max = DomainConstants.ORDER_CONTACT_SIZE)]
        val contact: String
)

data class ProductRequest(@field:[NotEmpty Size(max = DomainConstants.PRODUCT_ID_SIZE)] val productId: String)
