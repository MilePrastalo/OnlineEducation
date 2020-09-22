package rs.eeducation.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open abstract class User(@Id
                         @GeneratedValue
                         var id: Long?,
                         @Column
                         var email: String,
                         @Column
                         var name: String,
                         @Column
                         var userPassword: String,
                         @ManyToMany(fetch = FetchType.EAGER)
                         var authorityList: Set<Authority>,
                         @Column
                         var confirmed: Boolean
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorityList
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}
