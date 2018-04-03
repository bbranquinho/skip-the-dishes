package br.com.skip.the.dishes.query.resource.order

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

class OrderResourceTest : QueryResourceBaseTest() {

    companion object {
        const val DELIVERY_ADDRESS_DESCRIPTION = "Address for order delivery."
        const val CONTACT_DESCRIPTION = "Contact for order delivery."
        const val CUSTOMER_ID_DESCRIPTION = "Customer that is related with the new order."
        const val ORDER_ID_DESCRIPTION = "Order identifier."
        const val STATUS_DESCRIPTION = "Current order status."
        const val PRODUCTS_DESCRIPTION = "Products that are related with the order."
        const val SIZE_DESCRIPTION = "Number of elements by page."
        const val PAGE_DESCRIPTION = "Page number."
        const val TOTAL_ORDERS_DESCRIPTION = "The total number of orders."
        const val LINK_DESCRIPTION = "Links for next, last, and first pages."
    }

    @Test
    fun `Find orders without specifying a page`() {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .get("/api/v1/order")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "6"))
                .andExpect(MockMvcResultMatchers.header().string("Link", """</api/v1/order?page=0&size=20>; rel="last",</api/v1/order?page=0&size=20>; rel="first""""))
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                HeaderDocumentation.responseHeaders(
                                        HeaderDocumentation.headerWithName("X-Total-Count").description(TOTAL_ORDERS_DESCRIPTION),
                                        HeaderDocumentation.headerWithName("Link").description(LINK_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("[*].id").description(ORDER_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].customerId").description(CUSTOMER_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].deliveryAddress").description(DELIVERY_ADDRESS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].contact").description(CONTACT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].status").description(STATUS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].products").description(PRODUCTS_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Find orders for page 0 and size 3`() {
        val page = "0"
        val size = "3"

        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .get("/api/v1/order")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .param("page", page)
                                .param("size", size)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.header().string("X-Total-Count", "6"))
                .andExpect(MockMvcResultMatchers.header().string("Link", """</api/v1/order?page=1&size=3>; rel="next",</api/v1/order?page=1&size=3>; rel="last",</api/v1/order?page=0&size=3>; rel="first""""))
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.requestParameters(
                                        RequestDocumentation.parameterWithName("size").description(SIZE_DESCRIPTION),
                                        RequestDocumentation.parameterWithName("page").description(PAGE_DESCRIPTION)
                                ),
                                HeaderDocumentation.responseHeaders(
                                        HeaderDocumentation.headerWithName("X-Total-Count").description(TOTAL_ORDERS_DESCRIPTION),
                                        HeaderDocumentation.headerWithName("Link").description(LINK_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("[*].id").description(ORDER_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].customerId").description(CUSTOMER_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].deliveryAddress").description(DELIVERY_ADDRESS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].contact").description(CONTACT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].status").description(STATUS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("[*].products").description(PRODUCTS_DESCRIPTION)
                                )
                        )
                )
    }

    @Test
    fun `Find order by ID`() {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders
                                .get("/api/v1/order/{id}", "24ee7c24-2a06-11e8-b467-0ed5f89f718b")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .header(HttpHeaders.AUTHORIZATION, TOKEN)
                )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(
                        MockMvcRestDocumentation.document("{method-name}",
                                RequestDocumentation.pathParameters(
                                        RequestDocumentation.parameterWithName("id").description(ORDER_ID_DESCRIPTION)
                                ),
                                PayloadDocumentation.responseFields(
                                        PayloadDocumentation.fieldWithPath("id").description(ORDER_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("customerId").description(CUSTOMER_ID_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("deliveryAddress").description(DELIVERY_ADDRESS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("contact").description(CONTACT_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("status").description(STATUS_DESCRIPTION),
                                        PayloadDocumentation.fieldWithPath("products").description(PRODUCTS_DESCRIPTION)
                                )
                        )
                )
    }

}
