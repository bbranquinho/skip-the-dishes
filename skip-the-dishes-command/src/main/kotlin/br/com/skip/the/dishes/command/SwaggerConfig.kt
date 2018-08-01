package br.com.skip.the.dishes.command

import br.com.skip.the.dishes.command.resource.commons.Paths
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.SecurityReference
import springfox.documentation.service.OAuth
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant
import springfox.documentation.service.GrantType
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.SecurityConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig(
        @Value("\${server.version}")
        private val serverVersion: String,

        @Value("\${spring.application.name}")
        private val applicationName: String,

        @Value("\${keycloak.realm}")
        private val realm: String,

        @Value("\${swagger.auth.token-url:}")
        private val authTokenUrl: String,

        @Value("\${swagger.auth.client-id:}")
        private val authClientId: String
) {

    private companion object {
        const val PATH = Paths.ROOT_PATH + "/.*"
    }

    @Bean
    fun api() =
            Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.regex(PATH))
                    .build()
                    .securityContexts(arrayListOf(securityContext()))
                    .securitySchemes(arrayListOf(securitySchema()))

    @Bean
    fun securityConfiguration() =
            SecurityConfiguration(authClientId, "", realm, "", "", emptyMap(), false)

    private fun securitySchema() =
            OAuth("oauth2", emptyList(), listOf<GrantType>(ResourceOwnerPasswordCredentialsGrant(authTokenUrl)))

    private fun securityContext(): SecurityContext =
            SecurityContext.builder()
                    .securityReferences(arrayListOf(SecurityReference("oauth2", arrayOfNulls(0))))
                    .forPaths(PathSelectors.regex(PATH))
                    .build()

    private fun apiInfo(): ApiInfo =
            ApiInfoBuilder()
                    .title(applicationName)
                    .description("Swagger API")
                    .version(serverVersion)
                    .build()

}
