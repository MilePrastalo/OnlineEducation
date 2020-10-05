package rs.eeducation.dto

import rs.eeducation.model.QuestionType

class QuestionDto(var id: Long?,
                  var name: String,
                  var questionType: QuestionType,
                  var answer: List<AnswerDto>,
                  var questionPoints: Int) {
}
