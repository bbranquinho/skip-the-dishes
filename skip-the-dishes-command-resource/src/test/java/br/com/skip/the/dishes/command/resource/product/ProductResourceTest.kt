package br.com.skip.the.dishes.command.resource.product

import br.com.skip.the.dishes.command.resource.CommandResourceBaseTest
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
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
        val cartRequestContent = """{"name": "Rice","description": "Delicious rice","storeId": "24ee7c24-2a06-11e8-b467-0ed5f89f718b","price": 10.3}"""

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .content(cartRequestContent)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
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

}
