package rs.eeducation.dto

import rs.eeducation.model.Course
import java.util.*

class CourseDTO(course: Course) {
    var id: Long? = course.id
    var lessons: List<LessonDto>
    var students: List<StudentDto>
    var teachers: List<UserBasicDto>
    var school: SchoolDto?
    var grades: List<GradeDTO>
    var tests: List<TestDTO>
    var absences: List<AbsenceDTO>
    var name: String
    var begins: Date
    var ends: Date
    var description: String
    var studentsWaiting: List<UserBasicDto>

    init {
        lessons = course.lessons.map { lesson -> LessonDto(lesson, "") }
        students = course.students.map { student -> StudentDto(student) }
        teachers = course.teachers.map { teacher -> UserBasicDto(teacher.id, teacher.email, teacher.name) }
        school = if (course.school != null) {
            SchoolDto(course.school!!)
        } else {
            null
        }
        grades = course.grades.map { grade -> GradeDTO(grade.student.id!!, grade.student.name, course.id!!, course.name, grade.value) }
        tests = course.tests.map { test -> TestDTO(test) }
        absences = course.absences.map { absence -> AbsenceDTO(absence) }
        name = course.name
        begins = course.beginsDate
        ends = course.endsDate
        description = course.descr
        studentsWaiting = course.studentsWaiting.map { student -> UserBasicDto(student.id, student.email, student.name) }
    }
}
