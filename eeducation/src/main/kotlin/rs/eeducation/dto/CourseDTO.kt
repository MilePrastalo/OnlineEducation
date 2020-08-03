package rs.eeducation.dto

import rs.eeducation.model.Course
import java.util.*

class CourseDTO {
    var id: Long?
    var lections: List<String>
    var students: List<String>
    var teachers: List<String>
    var school: String
    var grades: List<GradeDTO>
    var tests: List<TestDTO>
    var absences: List<AbsenceDTO>
    var name: String
    var begins: Date
    var ends: Date

    constructor(course: Course) {
        id = course.id
        lections = course.lections.map { lection -> lection.name }
        students = course.students.map { student -> student.name }
        teachers = course.teachers.map { teacher -> teacher.name }
        school = course.name
        grades = course.grades.map { grade -> GradeDTO(grade.student.id!!, grade.student.name, course.id!!, course.name, grade.value) }
        tests = course.tests.map { test -> TestDTO(test) }
        absences = course.absences.map { absence -> AbsenceDTO(absence) }
        name = course.name
        begins = course.begins
        ends = course.ends
    }
}
