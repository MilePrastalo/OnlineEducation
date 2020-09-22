package rs.eeducation.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import rs.eeducation.repository.UserRepository
import javax.persistence.EntityNotFoundException


@Service
class JwtUserDetailsService(var userRepository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findOneByEmail(email).orElseThrow { EntityNotFoundException("User not found") }
    }

}
