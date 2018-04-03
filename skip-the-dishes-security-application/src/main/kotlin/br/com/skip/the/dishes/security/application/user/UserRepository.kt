package br.com.skip.the.dishes.security.application.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
interface UserRepository : JpaRepository<UserEntity, Long>, UserRepositoryCustom {

    fun findByEmail(email: String): UserEntity?

    override fun save(@Valid @RequestBody entity: UserEntity): UserEntity

}
