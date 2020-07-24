package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Role
import java.util.*

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String?): Optional<Role?>
}
