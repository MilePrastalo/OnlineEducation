package rs.eeducation.dto

import rs.eeducation.model.Absence
import rs.eeducation.model.Comment
import rs.eeducation.model.Lesson
import java.util.*
import kotlin.collections.ArrayList

class LessonDto {

    var id: Long?
    var absences: List<Absence>
    var comments: List<Comment>
    var lessonContent: String
    var name: String
    var date: Date

    constructor(id: Long?,
                absences: List<Absence>,
                comments: List<Comment>,
                lessonContent: String,
                name: String,
                date: Date) {
        this.id = id
        this.absences = absences
        this.comments = comments
        this.lessonContent = lessonContent
        this.name = name
        this.date = date
    }

    constructor(lesson: Lesson) {
        this.id = lesson.id
        this.lessonContent = ""
        this.absences = ArrayList()
        this.comments = ArrayList()
        this.name = lesson.name
        this.date = lesson.date
    }
}
