package br.com.skip.the.dishes.command.resource.product

import br.com.skip.the.dishes.command.resource.CommandResourceBaseTest
import br.com.skip.the.dishes.command.resource.order.OrderResourceTest
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ProductResourceTest : CommandResourceBaseTest() {

    companion object {
        const val PRODUCT_NAME_DESCRIPTION = "Product name."
        const val PRODUCT_ID_DESCRIPTION = "Identify the product."
        const val PRODUCT_DESCRIPTION = "Product description."
        const val STORE_ID_DESCRIPTION = "StoreID that is the owner of the product."
        const val PRICE_DESCRIPTION = "Product price."
    }

    @Test
    fun `Create a product with success`() {
        val productRequestContent = """{"name": "Rice","description": "Delicious rice","storeId": "24ee7c24-2a06-11e8-b467-0ed5f89f718b","price": 10.3}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .post("/api/v1/product")
                                .content(productRequestContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", CoreMatchers.notNullValue()))
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("name").description(PRODUCT_NAME_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("description").description(PRODUCT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("storeId").description(STORE_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("price").description(PRICE_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("productId").description(PRODUCT_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Update details for a product`() {
        val productDetailContent = """{"name": "Rice","description": "Delicious rice"}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .patch("/api/v1/product/{id}/detail", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .content(productDetailContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("id").description(OrderResourceTest.ORDER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("name").description(PRODUCT_NAME_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("description").description(PRODUCT_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Update price for a product`() {
        val productDetailContent = """{"price": 14.38}"""

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .patch("/api/v1/product/{id}/price", "d3597b9e-2eeb-11e8-b467-0ed5f89f718b")
                                .content(productDetailContent)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("id").description(OrderResourceTest.ORDER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.requestFields(
                                        PayloadDocumentation.fieldWithPath("price").description(PRICE_DESCRIPTION)
                                )
                        )
                )
    }

}