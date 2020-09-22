package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.eeducation.dto.SchoolDto
import rs.eeducation.service.TeacherService

@RestController
@RequestMapping(value = ["api/teacher"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class TeacherController(private val teacherService: TeacherService) {

    @GetMapping(value = ["schools"])
    @PreAuthorize("hasAuthority('TEACHER')")

    fun getTeacherSchools(): ResponseEntity<List<SchoolDto>> {
        val schools = teacherService.getTeacherSchools()
        val dto = schools.map { school -> SchoolDto(school) }
        return ResponseEntity(dto, HttpStatus.OK)
    }



}
