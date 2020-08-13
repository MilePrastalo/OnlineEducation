package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Course
import rs.eeducation.model.Grade
import rs.eeducation.model.Student

interface GradeRepository : JpaRepository<Grade, Long> {

    fun findAllByStudent(student: Student): List<Grade>

    fun findAllByCourse(course: Course): List<Grade>
}
