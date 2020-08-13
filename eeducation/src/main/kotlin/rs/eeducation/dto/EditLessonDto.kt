package rs.eeducation.dto

import java.util.*

class EditLessonDto(var id: Long,
                    var courseId: Long,
                    var lessonContent: String,
                    var name: String,
                    var date: Date) {
}
