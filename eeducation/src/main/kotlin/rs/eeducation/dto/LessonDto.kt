package rs.eeducation.dto

import rs.eeducation.model.Absence
import rs.eeducation.model.Comment
import rs.eeducation.model.Course
import java.util.*
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

class LessonDto(var id: Long?,
                var absences: List<Absence>,
                var comments: List<Comment>,
                var lessonContent: String,
                var name: String,
                var date: Date) {
}
