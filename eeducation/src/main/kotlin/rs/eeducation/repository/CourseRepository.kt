package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Course
import rs.eeducation.model.Teacher

interface CourseRepository : JpaRepository<Course, Long> {

    fun findByTeachersContaining(item: Teacher): List<Course>
    fun findByNameContaining(name: String): List<Course>
}
