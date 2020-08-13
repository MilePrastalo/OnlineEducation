package rs.eeducation.coverters

import rs.eeducation.dto.TestDTO
import rs.eeducation.model.Test

class TestConverter {

    companion object {
        fun toDto(test: Test): TestDTO {
            val questions = test.questions.map { question -> QuestionConverter.toDto(question) }
            return TestDTO(test.id, test.name, test.date, test.duration, test.testType, questions, test.course.id!!)
        }
    }

}
