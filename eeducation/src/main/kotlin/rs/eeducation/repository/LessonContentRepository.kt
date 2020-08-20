package rs.eeducation.repository

import org.springframework.data.mongodb.repository.MongoRepository
import rs.eeducation.model.LessonContent

interface LessonContentRepository : MongoRepository<LessonContent, String> {
    fun findByLessonId(lessonId: Long): LessonContent
}
