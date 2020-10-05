package rs.eeducation.dto

import rs.eeducation.model.ANSWER_TYPE

class AnswerDto(var id: Long?,
                var answerText: String,
                var correct: Boolean?,
                var answerType: ANSWER_TYPE?) {
}
