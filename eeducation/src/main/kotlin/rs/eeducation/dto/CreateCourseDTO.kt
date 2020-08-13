package rs.eeducation.dto

import java.util.*

class CreateCourseDTO(var name: String,
                      var begins: Date,
                      var ends: Date,
                      var school: Long,
                      var teachers: List<String>,
                      var freeToJoin: Boolean,
                      var description: String) {

}
