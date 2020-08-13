package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.AddCommentDTO
import rs.eeducation.dto.CommentDTO
import rs.eeducation.service.CommentService

@RestController
@RequestMapping(value = ["/api/comments"])
@CrossOrigin
class CommentController(private val commentService: CommentService) {

    //Get comments
    @GetMapping(value = ["{lessonId}"])
    fun getComments(@PathVariable("lessonId") lessonId: Long): ResponseEntity<List<CommentDTO>> {
        val comments = commentService.getComments(lessonId)
        val dto = comments.map { comment -> CommentDTO(comment) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Add comment
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addComment(@RequestBody addCommentDTO: AddCommentDTO): ResponseEntity<CommentDTO> {
        val comment = commentService.addComment(addCommentDTO)
        return ResponseEntity(CommentDTO(comment), HttpStatus.OK)
    }
}
