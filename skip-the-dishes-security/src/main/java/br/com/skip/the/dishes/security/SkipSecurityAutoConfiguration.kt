package br.com.skip.the.dishes.security

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

typealias Matchers = ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry

@ComponentScan
@EnableConfigurationProperties(SkipSecurityProperties::class)
@Configuration
class SkipSecurityAutoConfiguration(val skipSecurityProperties: SkipSecurityProperties) : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        val matchers: Matchers = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()

        skipSecurityProperties.constraints.forEach { applyConstraint(matchers, it) }
        skipSecurityProperties.publicConstraints.forEach { applyPublicConstraint(matchers, it) }

        matchers.anyRequest().fullyAuthenticated()
    }

    private fun applyConstraint(matchers: Matchers, constraint: SkipSecurityProperties.Constraint) {
        constraint
                .roles
                .forEach {
                    val methods = it.methods.map { HttpMethod.valueOf(it.toUpperCase()) }
                    applyMatcher(matchers, constraint.pattern!!, it.role!!, methods)
                }
    }

    private fun applyMatcher(matchers: Matchers, pattern: String, authority: String, methods: List<HttpMethod>) {
        if (methods.isEmpty()) {
            matchers.antMatchers(pattern).hasAnyAuthority(authority)
        } else {
            methods.forEach { matchers.antMatchers(it, pattern).hasAnyAuthority(authority) }
        }
    }

    private fun applyPublicConstraint(matchers: Matchers, publicConstraint: SkipSecurityProperties.PublicConstraint) {
        if (publicConstraint.methods.isEmpty()) {
            matchers.antMatchers(publicConstraint.pattern).permitAll()
        } else {
            publicConstraint
                    .methods
                    .map { HttpMethod.valueOf(it) }
                    .forEach { matchers.antMatchers(it, publicConstraint.pattern).permitAll() }
        }
    }

}
