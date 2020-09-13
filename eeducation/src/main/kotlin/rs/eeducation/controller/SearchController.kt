package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.CourseBasicDTO
import rs.eeducation.dto.SchoolDto
import rs.eeducation.service.SearchService

@RestController
@RequestMapping(value = ["api/search"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class SearchController(private val searchService: SearchService) {

    @GetMapping(value = ["course/{name}"])
    fun searchCourses(@PathVariable("name") name: String): ResponseEntity<List<CourseBasicDTO>> {
        val courses = searchService.searchCourses(name)
        val dto = courses.map { course -> CourseBasicDTO(course) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    @GetMapping(value = ["school/{name}"])
    fun searchSchools(@PathVariable("name") name: String): ResponseEntity<List<SchoolDto>> {
        val schools = searchService.searchSchools(name)
        val dto = schools.map { school -> SchoolDto(school) }
        return ResponseEntity(dto, HttpStatus.OK)
    }
}
