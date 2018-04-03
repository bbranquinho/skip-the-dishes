package br.com.skip.the.dishes.security.application.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

interface UserRepositoryCustom {
    fun save(entity: UserEntity): UserEntity
}

@Component
class UserRepositoryImpl(val passwordEncoder: PasswordEncoder) : UserRepositoryCustom {

    @Autowired
    lateinit var repository: JpaRepository<UserEntity, Long>

    override fun save(entity: UserEntity): UserEntity =
            entity
                    .copy(passwordd = passwordEncoder.encode(entity.passwordd))
                    .let { repository.save(it) }

}
