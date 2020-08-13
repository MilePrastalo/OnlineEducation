package rs.eeducation.dto

import rs.eeducation.model.School

class SchoolDto(school: School) {
    var id = school.id
    var name = school.name
}
