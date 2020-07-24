package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Course

interface CourseRepository : JpaRepository<Course, Long> {
}
