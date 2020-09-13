package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.eeducation.dto.CourseBasicDTO
import rs.eeducation.dto.SchoolDto
import rs.eeducation.service.StudentService

@RestController
@RequestMapping(value = ["api/students"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class StudentController(private val studentService: StudentService) {

    @GetMapping(value = ["schools"])
    fun getStudentsSchools(): ResponseEntity<List<SchoolDto>> {
        val schools = studentService.getStudentsSchools()
        val dto = schools.map { school -> SchoolDto(school) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    @GetMapping(value = ["courses"])
    fun getStudentsCourses(): ResponseEntity<List<CourseBasicDTO>> {
        val courses = studentService.getStudentCourses()
        val dto = courses.map { course -> CourseBasicDTO(course) }
        return ResponseEntity(dto, HttpStatus.OK)
    }


}
