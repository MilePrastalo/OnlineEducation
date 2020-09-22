package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Authority
import java.util.*

interface AuthorityRepository: JpaRepository<Authority,Long> {
    fun findByName(name: String?): Authority?

}
