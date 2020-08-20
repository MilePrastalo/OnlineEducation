package rs.eeducation.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class LessonContent(@Id
                    var id: String?,
                    var lessonId: Long,
                    var lessonContent: String) {

}
