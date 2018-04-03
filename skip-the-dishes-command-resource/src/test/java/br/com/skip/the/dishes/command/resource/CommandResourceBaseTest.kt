package br.com.skip.the.dishes.command.resource

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [CommandResourceConfigTest::class])
abstract class CommandResourceBaseTest {

    companion object {
        const val TOKEN = "Bearer \${JWT}"
        const val TOKEN_DESCRIPTION = "JWT token based on RSA signature."
    }

    @Rule
    @JvmField
    final val restDocumentation = JUnitRestDocumentation("target/generated-snippets")

    @Autowired
    protected lateinit var context: WebApplicationContext

    protected lateinit var mockMvc: MockMvc

    protected lateinit var document: RestDocumentationResultHandler

    @Before
    fun setUp() {
        document = MockMvcRestDocumentation
                .document(
                        "{method-name}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
                )
                .document(
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("Authorization").description(TOKEN_DESCRIPTION)
                        )
                )

        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation))
                .alwaysDo<DefaultMockMvcBuilder>(this.document)
                .build()

    }

}