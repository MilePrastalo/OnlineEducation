package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Teacher
import java.util.*

interface TeacherRepository : JpaRepository<Teacher, Long> {

    fun findByEmail(email: String): Optional<Teacher>
}
