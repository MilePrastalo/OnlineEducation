package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Question

interface QuestionRepository : JpaRepository<Question, Long> {
}
