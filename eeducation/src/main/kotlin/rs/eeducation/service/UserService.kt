package rs.eeducation.service

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import rs.eeducation.model.User
import rs.eeducation.repository.UserRepository


@Service
class UserService(private var userRepository: UserRepository) {

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun findByEmail(email: String): User {
        return userRepository.findOneByEmail(email).get()
    }

    fun getLoggedInUser(): User? {
        var email = ""
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        email = if (authentication !is AnonymousAuthenticationToken) {
            authentication.name
        } else {
            return null
        }
        return findByEmail(email)
    }


}
