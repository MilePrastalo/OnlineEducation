package rs.eeducation.coverters

import rs.eeducation.dto.AnswerDto
import rs.eeducation.model.Answer

class AnswerConverter {

    companion object {
        fun toDto(answer: Answer): AnswerDto {
            return AnswerDto(answer.id, answer.answerText, answer.correct)
        }

        fun toAnswer(dto: AnswerDto): Answer {
            return Answer(dto.id, dto.answerText, dto.correct)
        }
    }
}
