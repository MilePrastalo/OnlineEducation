package rs.eeducation.dto

import rs.eeducation.model.Absence

class AbsenceDTO(absence: Absence) {

    var studentName: String = absence.student.name
    var studentId: Long = absence.student.id!!
    var courseName: String = absence.course.name
    var courseId: Long = absence.course.id!!
    var lessonName: String = absence.lesson.name
    var lessonId: Long = absence.lesson.id!!

}
