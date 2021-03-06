package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.CreateLessonDto
import rs.eeducation.dto.EditLessonDto
import rs.eeducation.dto.LessonDto
import rs.eeducation.model.LessonContent
import rs.eeducation.service.LessonService

@RestController
@RequestMapping(value = ["api/lessons"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class LessonController(private val lessonService: LessonService) {

    //Create lesson
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('TEACHER')")
    fun createLesson(@RequestBody createLessonDto: CreateLessonDto): ResponseEntity<LessonDto> {
        val lesson = lessonService.createLesson(createLessonDto)
        val dto = LessonDto(lesson, createLessonDto.lessonContent,lesson.lessonContentId)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Edit lesson
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('TEACHER')")
    fun editLesson(@RequestBody editLessonDto: EditLessonDto): ResponseEntity<LessonDto> {
        val lesson = lessonService.editLesson(editLessonDto)
        val dto = LessonDto(lesson, editLessonDto.lessonContent,editLessonDto.lessonContent)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Delete lesson
    @DeleteMapping(value = ["{lessonId}"])
    @PreAuthorize("hasAuthority('TEACHER')")
    fun deleteLesson(@PathVariable("lessonId") lessonId: Long): ResponseEntity<Void> {
        lessonService.deleteLesson(lessonId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Get lesson
    @GetMapping(value = ["{lessonId}"])
    fun getLesson(@PathVariable("lessonId") lessonId: Long): ResponseEntity<LessonDto> {
        val lesson = lessonService.findById(lessonId)
        val lessonContent = try{
            lessonService.getLessonContent(lesson)
        }catch (e:Exception){
            LessonContent("","")
        }
        val dto = LessonDto(lesson, lessonContent.lessonContent, lessonContent.id!!)
        return ResponseEntity(dto, HttpStatus.OK)
    }


}
