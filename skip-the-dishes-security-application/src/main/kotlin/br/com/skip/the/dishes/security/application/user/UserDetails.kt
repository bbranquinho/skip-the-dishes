package br.com.skip.the.dishes.security.application.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetails(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails =
            userRepository.findByEmail(email) ?:
                    throw UsernameNotFoundException("""User [$email] not found.""")

}
