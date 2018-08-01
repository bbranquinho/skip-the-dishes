package br.com.skip.the.dishes.command.resource.order

import br.com.skip.the.dishes.domain.order.DeliveryAddress
import br.com.skip.the.dishes.domain.order.Order
import br.com.skip.the.dishes.domain.order.elements.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid
import javax.validation.constraints.Size

import br.com.skip.the.dishes.domain.utils.DomainConstants.ORDER_ID_SIZE

@RestController
class OrderResource(private val commandHandler: CommandHandler) : OrderApi {

    override fun create(@RequestBody @Valid createRequest: CreateRequest): CreateResponse {
        val command = CreateOrderCommand(createRequest.customerId)
        val order = commandHandler.handle(command)
        return CreateResponse(order.getOrderId())
    }

    override fun addProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String,
                            @RequestBody @Valid productRequest: ProductRequest) {
        AddProductCommand(id, productRequest.productId)
                .apply(commandHandler::handle)
    }

    override fun deleteProduct(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String,
                               @RequestBody @Valid productRequest: ProductRequest) {
        DeleteProductCommand(id, productRequest.productId)
                .apply(commandHandler::handle)
    }

    override fun cancelOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String) {
        CancelOrderCommand(id)
                .apply(commandHandler::handle)
    }

    override fun requestOrder(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String) {
        RequestOrderCommand(id)
                .apply(commandHandler::handle)
    }

    override fun updateDeliveryAddress(@PathVariable("id") @Valid @Size(max = ORDER_ID_SIZE) id: String,
                                       @RequestBody @Valid deliveryAddressRequest: DeliveryAddressRequest) {
        val deliveryAddress = DeliveryAddress(deliveryAddressRequest.deliveryAddress, deliveryAddressRequest.contact)

        UpdateDeliveryAddressCommand(id, deliveryAddress)
                .apply(commandHandler::handle)
    }

}
