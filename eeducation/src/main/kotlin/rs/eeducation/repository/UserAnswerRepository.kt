package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.UserAnswer

interface UserAnswerRepository : JpaRepository<UserAnswer, Long> {
}
