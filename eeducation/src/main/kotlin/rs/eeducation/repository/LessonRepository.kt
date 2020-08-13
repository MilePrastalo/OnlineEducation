package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Lesson

interface LessonRepository : JpaRepository<Lesson, Long> {
}
