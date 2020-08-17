package rs.eeducation.dto

import rs.eeducation.model.Student

class StudentDto {
    var id: Long?
    var email: String
    var name: String

    constructor(id: Long?, email: String, name: String) {
        this.id = id
        this.email = email
        this.name = name
    }

    constructor(student: Student) {
        this.id = student.id
        this.name = student.name
        this.email = student.email
    }
}
