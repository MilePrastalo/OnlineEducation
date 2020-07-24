package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.User
import java.util.*

interface UserRepository: JpaRepository<User,Long> {
    fun findOneByEmail(email:String):Optional<User>
}
