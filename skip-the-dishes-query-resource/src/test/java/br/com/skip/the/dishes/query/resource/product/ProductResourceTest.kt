package br.com.skip.the.dishes.query.resource.product

import br.com.skip.the.dishes.query.resource.QueryResourceBaseTest
import org.junit.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ProductResourceTest : QueryResourceBaseTest() {

    companion object {
        const val PRODUCT_NAME_DESCRIPTION = "Product name."
        const val PRODUCT_ID_DESCRIPTION = "Identify the product."
        const val PRODUCT_DESCRIPTION = "Product description."
        const val STORE_ID_DESCRIPTION = "StoreID that is the owner of the product."
        const val PRICE_DESCRIPTION = "Product price."
        const val SIZE_DESCRIPTION = "Number of elements by page."
        const val PAGE_DESCRIPTION = "Page number."
        const val TOTAL_PRODUCTS_DESCRIPTION = "The total number of products."
        const val LINK_DESCRIPTION = "Links for next, last, and first pages."
    }

    @Test
    fun `Find products without specifying a page`() {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .get("/api/v1/product")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "6"))
                .andExpect(MockMvcResultMatchers.header().string("Link", """</api/v1/product?page=0&size=20>; rel="last",</api/v1/product?page=0&size=20>; rel="first""""))
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                HeaderDocumentation.responseHeaders(
                                        HeaderDocumentation.headerWithName("X-Total-Count").description(TOTAL_PRODUCTS_DESCRIPTION),
                                        HeaderDocumentation.headerWithName("Link").description(LINK_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("[*].id").description(PRODUCT_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].name").description(PRODUCT_NAME_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].description").description(PRODUCT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].price").description(PRICE_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].storeId").description(STORE_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Find products for page 0 and size 3`() {
        val page = "0"
        val size = "3"

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .get("/api/v1/product")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .param("page", page)
                                .param("size", size)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "6"))
                .andExpect(MockMvcResultMatchers.header().string("Link", """</api/v1/product?page=1&size=3>; rel="next",</api/v1/product?page=1&size=3>; rel="last",</api/v1/product?page=0&size=3>; rel="first""""))
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.requestParameters(
                                        RequestDocumentation.parameterWithName("size").description(SIZE_DESCRIPTION),
                                        RequestDocumentation.parameterWithName("page").description(PAGE_DESCRIPTION)
                                ),
                                HeaderDocumentation.responseHeaders(
                                        HeaderDocumentation.headerWithName("X-Total-Count").description(TOTAL_PRODUCTS_DESCRIPTION),
                                        HeaderDocumentation.headerWithName("Link").description(LINK_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("[*].id").description(PRODUCT_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].name").description(PRODUCT_NAME_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].description").description(PRODUCT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].price").description(PRICE_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].storeId").description(STORE_ID_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Find product by ID`() {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .get("/api/v1/product/{id}", "24ee7c24-2a06-11e8-b467-0ed5f89f718b")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("id").description(PRODUCT_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("id").description(PRODUCT_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("name").description(PRODUCT_NAME_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("description").description(PRODUCT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("price").description(PRICE_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("storeId").description(STORE_ID_DESCRIPTION)
                                )
                        )
                )
    }

}
