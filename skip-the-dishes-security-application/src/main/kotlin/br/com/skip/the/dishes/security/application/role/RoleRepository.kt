package br.com.skip.the.dishes.security.application.role

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
interface RoleRepository : JpaRepository<RoleEntity, Long>
