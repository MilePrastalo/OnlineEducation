package rs.eeducation.dto

import java.util.*

class CreateLessonDto(var courseId: Long,
                      var lessonContent: String,
                      var name: String,
                      var date: Date) {
}
