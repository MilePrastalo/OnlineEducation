package rs.eeducation.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import rs.eeducation.model.Privilege
import rs.eeducation.model.Role
import rs.eeducation.model.User
import rs.eeducation.repository.UserRepository
import java.util.*


@Service
class JwtUserDetailsService(var userRepository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user: User = userRepository.findOneByEmail(email).get()
        return org.springframework.security.core.userdetails.User(user.email,
                user.password, getGrantedAuthorities(getPrivileges(user.roles)))
    }

    private fun getPrivileges(roles: Collection<Role>): List<String> {
        val privileges: MutableList<String> = ArrayList()
        val collection: MutableList<Privilege> = ArrayList<Privilege>()
        for (role in roles) {
            collection.addAll(role.privileges)
        }
        for (item in collection) {
            privileges.add(item.name)
        }
        return privileges
    }

    private fun getGrantedAuthorities(privileges: List<String>): List<GrantedAuthority>? {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        for (privilege in privileges) {
            authorities.add(SimpleGrantedAuthority(privilege))
        }
        return authorities
    }

}
