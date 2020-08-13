package rs.eeducation.coverters

import rs.eeducation.dto.QuestionDto
import rs.eeducation.model.Question

class QuestionConverter {

    companion object {
        fun toDto(question: Question): QuestionDto {
            return QuestionDto(question.id, question.name, question.questionType, question.answer.map { answer -> AnswerConverter.toDto(answer) })
        }

        fun toQuestion(dto: QuestionDto): Question {
            return Question(dto.id, dto.name, dto.questionType, dto.answer.map { answerDto -> AnswerConverter.toAnswer(answerDto) }.toSet())
        }
    }
}
