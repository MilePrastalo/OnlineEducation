package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.coverters.TestConverter
import rs.eeducation.dto.CreateTestDto
import rs.eeducation.dto.TestDTO
import rs.eeducation.service.TestService

@RestController
@RequestMapping(value = ["api/test"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class TestController(private val testService: TestService) {

    //Create Test
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTest(@RequestBody createTestDto: CreateTestDto): ResponseEntity<TestDTO> {
        val test = testService.createTest(createTestDto)
        val dto = TestConverter.toDto(test)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Delete Test
    @DeleteMapping(value = ["{testId}"])
    fun deleteTest(@PathVariable("testId") testId: Long): ResponseEntity<Void> {
        testService.delete(testId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Student submits test
    @PostMapping(value = ["student-test"])
    fun studentSubmitsTest() {

    }

    //Teacher views list of tests that have to be graded
    fun viewTestsToBeGraded() {

    }

    //View students test answers
    fun viewStudentTestAnswers() {}


    //Teacher grades test that is not self grade-ing
    fun teacherGradesTest() {

    }
}
