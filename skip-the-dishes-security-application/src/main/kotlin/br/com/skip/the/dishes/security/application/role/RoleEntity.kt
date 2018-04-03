package br.com.skip.the.dishes.security.application.role

import br.com.skip.the.dishes.security.application.commons.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
@Table(name = "role")
data class RoleEntity(
        @NotEmpty
        @Size(min = 4, max = 60)
        @Column(length = 60, nullable = false)
        val role: String
) : BaseEntity<Long>()
