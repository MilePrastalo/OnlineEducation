package rs.eeducation.dto

import rs.eeducation.model.Course
import java.util.*

//DTO when showing many courses
class CourseBasicDTO {
    var id: Long?
    var name: String
    var teachers: List<String>
    var school: String
    var begins: Date
    var ends: Date

    constructor(course: Course) {
        name = course.name
        id = course.id
        teachers = course.teachers.map { teacher -> teacher.name }
        school = course.school.name
        begins = course.begins
        ends = course.ends
    }

}
