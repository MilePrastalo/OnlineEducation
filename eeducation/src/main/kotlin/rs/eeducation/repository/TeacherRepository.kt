package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Teacher

interface TeacherRepository : JpaRepository<Teacher, Long> {
}
