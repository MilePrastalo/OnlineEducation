package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.AddCommentDTO
import rs.eeducation.model.Comment
import rs.eeducation.repository.CommentRepository
import java.util.*
import javax.persistence.EntityNotFoundException
import kotlin.collections.HashSet

@Service
class CommentService(private val commentRepository: CommentRepository,
                     private val lessonService: LessonService,
                     private val userService: UserService) {

    fun findById(commentId: Long): Comment {
        return commentRepository.findById(commentId).orElseThrow { EntityNotFoundException("Comment not exist") }
    }

    fun save(comment: Comment): Comment {
        return commentRepository.save(comment)
    }

    fun getComments(lessonId: Long): List<Comment> {
        val lesson = lessonService.findById(lessonId)
        return lesson.comments.toList()
    }

    fun addComment(addCommentDTO: AddCommentDTO): Comment {
        val user = userService.getLoggedInUser()
        val reply = addCommentDTO.replyTo != null
        var comment = Comment(null, user!!, HashSet(), addCommentDTO.text, Date(), reply)
        comment = commentRepository.save(comment)

        if (reply) {
            val parentComment = findById(addCommentDTO.replyTo!!)
            (parentComment.replies as MutableSet).add(comment)
            save(parentComment)
        }
        val lesson = lessonService.findById(addCommentDTO.lessonId);
        (lesson.comments as MutableSet).add(comment);
        lessonService.save(lesson)
        return comment
    }

}
