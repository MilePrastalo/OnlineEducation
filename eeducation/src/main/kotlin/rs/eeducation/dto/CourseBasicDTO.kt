package rs.eeducation.dto

import rs.eeducation.model.Course
import java.util.*

//DTO when showing many courses
class CourseBasicDTO(course: Course) {
    var id: Long? = course.id
    var name: String = course.name
    var teachers: List<String>
    var school: String?
    var begins: Date
    var ends: Date

    init {
        teachers = course.teachers.map { teacher -> teacher.name }
        school = course.school?.name
        begins = course.beginsDate
        ends = course.endsDate
    }

}
