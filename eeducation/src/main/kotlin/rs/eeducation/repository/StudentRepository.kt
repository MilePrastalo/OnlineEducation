package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Student

interface StudentRepository : JpaRepository<Student, Long> {
}
