package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.*
import rs.eeducation.service.CourseService

@RestController
@RequestMapping(value = ["api/courses"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class CourseController(private val courseService: CourseService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAnyAuthority('TEACHER','SCHOOL')")
    fun addCourse(@RequestBody createCourseDTO: CreateCourseDTO): ResponseEntity<CourseDTO> {
        val course = courseService.createCourse(createCourseDTO)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    @GetMapping(value = ["{courseId}"])
    fun getCourse(@PathVariable("courseId") courseId: Long): ResponseEntity<CourseDTO> {
        val course = courseService.findById(courseId)
        val dto = CourseDTO(course)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    @GetMapping(value = ["teacher"])
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    fun getTeacherCourses(): ResponseEntity<List<CourseBasicDTO>> {
        val courses = courseService.getTeacherCourses()
        val dtos = courses.map { course -> CourseBasicDTO(course) }
        return ResponseEntity(dtos, HttpStatus.OK)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAnyAuthority('TEACHER','SCHOOL')")
    fun editCourse(@RequestBody editCourseDTO: EditCourseDTO): ResponseEntity<CourseDTO> {
        //edit and return edited course
        val course = courseService.editCourse(editCourseDTO)
        //return dto
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    @DeleteMapping(value = ["{courseId}"])
    @PreAuthorize("hasAnyAuthority('TEACHER','SCHOOL')")
    fun archiveCourse(@PathVariable("courseId") courseId: Long): ResponseEntity<CourseDTO> {
        val course = courseService.archiveCourse(courseId)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    //Student join course
    @GetMapping(value = ["join/{courseId}"])
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    fun joinCourse(@PathVariable("courseId") courseId: Long): ResponseEntity<CourseDTO> {
        val course = courseService.joinCourse(courseId)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    //View Students of class that teacher is teaching
    @GetMapping(value = ["students/{courseId}"])
    fun viewStudentsOfClass(@PathVariable("courseId") courseId: Long): ResponseEntity<List<StudentDto>> {
        val students = courseService.findStudentsOfClass(courseId)
        val dto = students.map { student -> StudentDto(student.id, student.email, student.name) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    @GetMapping(value = ["acceptStudent/{courseId}/{studentId}"])
    @PreAuthorize("hasAnyAuthority('TEACHER','SCHOOL')")
    fun acceptStudentRequest(@PathVariable("courseId") courseId: Long, @PathVariable("studentId") studentId: Long)
            : ResponseEntity<CourseDTO> {
        val course = courseService.acceptStudentRequest(courseId, studentId)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }

    @GetMapping(value = ["rejectStudent/{courseId}/{studentId}"])
    @PreAuthorize("hasAnyAuthority('TEACHER','SCHOOL')")
    fun rejectStudentRequest(@PathVariable("courseId") courseId: Long, @PathVariable("studentId") studentId: Long)
            : ResponseEntity<CourseDTO> {
        val course = courseService.rejectStudentRequest(courseId, studentId)
        return ResponseEntity(CourseDTO(course), HttpStatus.OK)
    }
}
