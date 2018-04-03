package br.com.skip.the.dishes.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "skip", ignoreUnknownFields = true)
data class SkipSecurityProperties(
        var constraints: List<Constraint> = emptyList(),

        var publicConstraints: List<PublicConstraint> = emptyList()
) {

    data class Constraint(
            var pattern: String? = null,
            var roles: List<Role> = emptyList()
    )

    data class PublicConstraint(
            var pattern: String? = null,
            var methods: List<String> = emptyList()
    )

    data class Role(
            var role: String? = null,
            var methods: List<String> = emptyList()
    )

}
