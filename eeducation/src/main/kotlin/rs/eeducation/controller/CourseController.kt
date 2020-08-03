package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.CourseBasicDTO
import rs.eeducation.dto.CourseDTO
import rs.eeducation.dto.CreateCourseDTO
import rs.eeducation.dto.EditCourseDTO
import rs.eeducation.service.CourseService

@RestController
@RequestMapping(value = ["api/courses"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CourseController(private val courseService: CourseService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addCourse(@RequestBody createCourseDTO: CreateCourseDTO): ResponseEntity<CourseDTO> {
        val course = courseService.createCourse(createCourseDTO)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    @GetMapping(value = ["{teacherId}"])
    fun getTeacherCourses(@PathVariable("teacherId") teacherId: Long): ResponseEntity<List<CourseBasicDTO>> {
        val courses = courseService.getTeacherCourses(teacherId)
        val dtos = courses.map { course -> CourseBasicDTO(course) }
        return ResponseEntity(dtos, HttpStatus.OK)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun editCourse(@RequestBody editCourseDTO: EditCourseDTO): ResponseEntity<CourseDTO> {
        //edit and return edited course
        val course = courseService.editCourse(editCourseDTO)
        //return dto
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    @DeleteMapping(value = ["{courseId}"])
    fun archiveCourse(@PathVariable("courseId") courseId: Long): ResponseEntity<CourseDTO> {
        val course = courseService.archiveCourse(courseId)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }




}
