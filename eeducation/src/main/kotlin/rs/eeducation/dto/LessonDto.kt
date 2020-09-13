package rs.eeducation.dto

import rs.eeducation.model.Absence
import rs.eeducation.model.Lesson
import java.util.*
import kotlin.collections.ArrayList

class LessonDto {

    var id: Long?
    var absences: List<Absence>
    var comments: List<CommentDTO>
    var lessonContent: String
    var name: String
    var date: Date
    var courseId: Long
    var lessonLink: String

    constructor(id: Long?,
                absences: List<Absence>,
                comments: List<CommentDTO>,
                lessonContent: String,
                name: String,
                date: Date,
                courseId: Long,
                lessonLink: String) {
        this.id = id
        this.absences = absences
        this.comments = comments
        this.lessonContent = lessonContent
        this.name = name
        this.date = date
        this.courseId = courseId
        this.lessonLink = lessonLink
    }

    constructor(lesson: Lesson, content: String, link: String) {
        this.id = lesson.id
        this.lessonContent = content
        this.absences = ArrayList()
        this.comments = lesson.comments.map { comment -> CommentDTO(comment) }
        this.name = lesson.name
        this.date = lesson.date
        this.courseId = lesson.course.id!!
        this.lessonLink = link
    }
}
