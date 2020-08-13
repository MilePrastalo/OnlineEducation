package rs.eeducation.dto

import rs.eeducation.model.Comment

class CommentDTO(comment: Comment) {
    var id = comment.id
    var user = comment.user.name
    var datePosted = comment.datePosted
    var replies = comment.replies
}
