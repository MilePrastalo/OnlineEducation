package rs.eeducation.dto

import rs.eeducation.model.Comment

class CommentDTO(comment: Comment) {
    var id = comment.id
    var user = comment.user.name
    var datePosted = comment.datePosted
    var replies: List<CommentDTO> = comment.replies.map { comment -> CommentDTO(comment) }
    var text = comment.text
    var reply = comment.reply
}
