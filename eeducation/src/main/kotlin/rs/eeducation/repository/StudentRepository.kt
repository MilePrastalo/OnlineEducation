package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Student
import java.util.*

interface StudentRepository : JpaRepository<Student, Long> {

    fun findByEmail(email: String): Optional<Student>
}
