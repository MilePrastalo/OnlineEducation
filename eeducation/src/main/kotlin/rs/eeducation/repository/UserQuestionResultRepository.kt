package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.UserQuestionResult

interface UserQuestionResultRepository : JpaRepository<UserQuestionResult, Long> {
}
