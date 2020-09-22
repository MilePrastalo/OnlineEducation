package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.AddGradeDto
import rs.eeducation.dto.GradeDTO
import rs.eeducation.service.GradeService

@RestController
@RequestMapping(value = ["api/grades"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class GradeController(private val gradeService: GradeService) {

    //Teacher adds grade to student
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    fun teacherAddsGrade(@RequestBody addGradeDto: AddGradeDto): ResponseEntity<GradeDTO> {
        val grade = gradeService.addGrade(addGradeDto)
        val dto = GradeDTO(grade.student.id!!, grade.student.name, grade.course.id!!, grade.course.name, grade.value)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Student Views Grade
    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    fun studentViewGrade(): ResponseEntity<List<GradeDTO>> {
        val grades = gradeService.studentGrades()
        val dto = grades.map { grade -> GradeDTO(grade.student.id!!, grade.student.name, grade.course.id!!, grade.course.name, grade.value) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Teacher Views course Grade
    @GetMapping(value = ["{courseId}"])
    @PreAuthorize("hasAnyAuthority('TEACHER','SCHOOL')")
    fun viewCourseGrades(@PathVariable("courseId") courseId: Long): ResponseEntity<List<GradeDTO>> {
        val grades = gradeService.courseGrades(courseId)
        val dto = grades.map { grade -> GradeDTO(grade.student.id!!, grade.student.name, grade.course.id!!, grade.course.name, grade.value) }
        return ResponseEntity(dto, HttpStatus.OK)
    }
}
