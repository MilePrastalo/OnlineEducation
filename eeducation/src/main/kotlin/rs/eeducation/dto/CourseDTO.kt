package rs.eeducation.dto

import rs.eeducation.model.Course
import java.util.*

class CourseDTO(course: Course) {
    var id: Long? = course.id
    var lessons: List<LessonDto>
    var students: List<StudentDto>
    var teachers: List<String>
    var school: String
    var grades: List<GradeDTO>
    var tests: List<TestDTO>
    var absences: List<AbsenceDTO>
    var name: String
    var begins: Date
    var ends: Date
    var description: String

    init {
        lessons = course.lessons.map { lesson -> LessonDto(lesson,"") }
        students = course.students.map { student -> StudentDto(student) }
        teachers = course.teachers.map { teacher -> teacher.name }
        school = course.name
        grades = course.grades.map { grade -> GradeDTO(grade.student.id!!, grade.student.name, course.id!!, course.name, grade.value) }
        tests = course.tests.map { test -> TestDTO(test) }
        absences = course.absences.map { absence -> AbsenceDTO(absence) }
        name = course.name
        begins = course.beginsDate
        ends = course.endsDate
        description = course.descr
    }
}
