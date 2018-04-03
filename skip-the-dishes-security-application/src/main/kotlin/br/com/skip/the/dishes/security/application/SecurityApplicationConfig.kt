package br.com.skip.the.dishes.security.application

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
@ComponentScan(basePackages = ["br.com.skip.the.dishes.security"])
@Configuration
@EnableAutoConfiguration
class SecurityApplicationConfig(
        @Value("\${skip.key-store.alias}") val alias: String,
        @Value("\${skip.key-store.jsk-file}") val jskFile: String,
        @Value("\${skip.key-store.password}") val password: String) {

    @Bean
    fun getStandardPasswordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val keyPair = KeyStoreKeyFactory(ClassPathResource(jskFile), password.toCharArray())
                .getKeyPair(alias, password.toCharArray())

        return JwtAccessTokenConverter().apply { setKeyPair(keyPair) }
    }

}
