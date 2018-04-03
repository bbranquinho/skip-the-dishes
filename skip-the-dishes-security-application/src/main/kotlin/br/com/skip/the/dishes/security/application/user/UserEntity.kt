package br.com.skip.the.dishes.security.application.user

import br.com.skip.the.dishes.security.application.commons.BaseEntity
import br.com.skip.the.dishes.security.application.role.RoleEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class UserEntity(
        @field:[Size(min = 4, max = 255)]
        @Column(length = 255, nullable = false)
        val name: String,

        @field:[Email Size(min = 4, max = 255)]
        @Column(name = "email", length = 255, nullable = false, unique = true)
        val email: String,

        @field:[Valid NotEmpty Size(min = 4, max = 60)]
        @get:JsonIgnore
        @set:JsonProperty("password")
        @Column(name = "password", length = 80, nullable = false)
        var passwordd: String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_role",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")]
        )
        val roles: List<RoleEntity>?
) : BaseEntity<Long>(), UserDetails {

    @JsonIgnore
    override fun getAuthorities(): List<GrantedAuthority> =
            roles?.map { SimpleGrantedAuthority(it.role) } ?: emptyList()

    @JsonIgnore
    override fun getUsername(): String =
            email

    @JsonIgnore
    override fun getPassword(): String =
            passwordd

    @JsonIgnore
    override fun isEnabled(): Boolean =
            true

    @JsonIgnore
    override fun isCredentialsNonExpired() =
            true

    @JsonIgnore
    override fun isAccountNonExpired() =
            true

    @JsonIgnore
    override fun isAccountNonLocked() =
            true

}
