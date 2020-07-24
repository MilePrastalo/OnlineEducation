package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Comment

interface CommentRepository : JpaRepository<Comment, Long> {
}
