package br.com.skip.the.dishes.command.resource.order

import br.com.skip.the.dishes.command.resource.CommandResourceBaseTest
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class OrderResourceTest : CommandResourceBaseTest() {

    companion object {
        const val DELIVERY_ADDRESS_DESCRIPTION = "Address for order delivery."
        const val CONTACT_DESCRIPTION = "Contact for order delivery."
        const val CUSTOMER_ID_DESCRIPTION = "Customer that is related with the new order."
        const val ORDER_ID_DESCRIPTION = "Order identifier."
        const val PRODUCT_ID_DESCRIPTION = "Product to be added for an order."
    }

    @Test
    fun `Create a new order for a specific customer`() {
        val orderContent = """{"customerId":"user@skip.ca"}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .post("/api/v1/order")
                                .content(orderContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", CoreMatchers.notNullValue()))
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("customerId").description(CUSTOMER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("orderId").description(ORDER_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Add a product for an order`() {
        val productContent = """{"productId":"e79347b6-2eeb-11e8-b467-0ed5f89f718b"}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .post("/api/v1/order/{orderId}/product", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .content(productContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("orderId").description(ORDER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("productId").description(PRODUCT_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Delete a product from an order`() {
        val productContent = """{"productId":"e79347b6-2eeb-11e8-b467-0ed5f89f718b"}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .delete("/api/v1/order/{orderId}/product", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .content(productContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("orderId").description(ORDER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("productId").description(PRODUCT_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Cancel an order`() {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .patch("/api/v1/order/{orderId}/cancel", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("orderId").description(ORDER_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Request an order that will be done and delivered`() {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .patch("/api/v1/order/{orderId}/request", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("orderId").description(ORDER_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Update the delivery address for an order`() {
        val deliveryAddressContent = """{"deliveryAddress":"1234 North Fraser Way Suite","contact":"551134999999999"}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .patch("/api/v1/order/{orderId}/delivery_address", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .content(deliveryAddressContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("orderId").description(ORDER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("deliveryAddress").description(DELIVERY_ADDRESS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("contact").description(CONTACT_DESCRIPTION)
                                )
                        )
                )
    }

}
