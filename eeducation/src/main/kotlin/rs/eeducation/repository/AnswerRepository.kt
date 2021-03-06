package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Answer

interface AnswerRepository : JpaRepository<Answer, Long> {
}
