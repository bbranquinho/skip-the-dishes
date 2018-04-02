package br.com.skip.the.dishes.security.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.StandardPasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@ComponentScan(basePackages = ["br.com.skip.the.dishes.security"])
@Configuration
class SecurityApplicationConfig {

    @Bean
    fun getStandardPasswordEncoder(): PasswordEncoder {
        return StandardPasswordEncoder("secret")
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val keyPair = KeyStoreKeyFactory(
                ClassPathResource("skipdishes.jks"), "skipdishes".toCharArray())
                .getKeyPair("skipdishes", "skipdishes".toCharArray())

        return JwtAccessTokenConverter().apply { setKeyPair(keyPair) }
    }

}
