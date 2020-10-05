package rs.eeducation.dto

import rs.eeducation.coverters.AnswerConverter
import rs.eeducation.model.TestResults

class TestResultViewDto {
    var id: Long?
    var student: StudentDto
    var test: TestDTO
    var userQuestionResults: List<UserQuestionResultDto>

    constructor(testResult: TestResults) {
        id = testResult.id
        student = StudentDto(testResult.student)
        test = TestDTO(testResult.test)
        userQuestionResults = testResult.userQuestionResults.map { userQuestionResult ->
            UserQuestionResultDto(userQuestionResult.question.id!!, userQuestionResult.userAnswer.map { userAnswer ->
                if (userAnswer.answer != null) {
                    AnswerConverter.toDto(userAnswer.answer!!)
                } else {
                    AnswerDto(null, userAnswer.text, null, null)
                }
            })
        }
    }
}
