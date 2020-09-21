package rs.eeducation.dto

import rs.eeducation.coverters.QuestionConverter
import rs.eeducation.model.Test
import rs.eeducation.model.TestType
import java.text.SimpleDateFormat
import java.util.*

class TestDTO {

    var id: Long?
    var name: String
    var date: String
    var duration: Int
    var testType: TestType
    var questions: List<QuestionDto>
    var courseId: Long

    constructor(test: Test) {
        id = test.id
        name = test.name
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm")
        date = simpleDateFormat.format(test.date)
        duration = test.duration
        testType = test.testType
        questions = test.questions.map { question -> QuestionConverter.toDto(question) }.toList()
        courseId = test.course.id!!
    }

    constructor(id: Long?,
                name: String,
                date: Date,
                duration: Int,
                testType: TestType,
                questions: List<QuestionDto>,
                courseId: Long) {
        this.id = id
        this.name = name
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm")
        this.date = simpleDateFormat.format(date)
        this.duration = duration
        this.testType = testType
        this.questions = questions
        this.courseId = courseId
    }

}
