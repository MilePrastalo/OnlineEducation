package rs.eeducation.dto

import rs.eeducation.model.School

class SchoolDetailsDto(school: School) {
    var id: Long? = school.id
    var email: String = school.email
    var name: String = school.name
    var courses: List<CourseBasicDTO> = school.courses.map { course -> CourseBasicDTO(course) }
    var teachers: List<UserBasicDto> = school.teachers.map { teacher -> UserBasicDto(teacher.id, teacher.email, teacher.name) }
    var students: List<UserBasicDto> = school.students.map { student -> UserBasicDto(student.id, student.email, student.name) }
    var teacherRequests:List<UserBasicDto> = school.teacherRequest.map { teacher -> UserBasicDto(teacher.id,teacher.email,teacher.name) }
    var studentRequests:List<UserBasicDto> = school.studentRequest.map { student -> UserBasicDto(student.id,student.email,student.name) }
}
