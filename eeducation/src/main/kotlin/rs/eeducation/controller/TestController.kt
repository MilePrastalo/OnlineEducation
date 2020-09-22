package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.eeducation.coverters.TestConverter
import rs.eeducation.dto.CreateTestDto
import rs.eeducation.dto.TestDTO
import rs.eeducation.dto.TestResultDto
import rs.eeducation.service.TestService
import java.util.*
import kotlin.collections.ArrayList

@RestController
@RequestMapping(value = ["api/test"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class TestController(private val testService: TestService) {

    //Create Test
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('TEACHER')")
    fun createTest(@RequestBody createTestDto: CreateTestDto): ResponseEntity<TestDTO> {
        val test = testService.createTest(createTestDto)
        val dto = TestConverter.toDto(test)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Delete Test
    @DeleteMapping(value = ["{testId}"])
    @PreAuthorize("hasAuthority('TEACHER')")
    fun deleteTest(@PathVariable("testId") testId: Long): ResponseEntity<Void> {
        testService.delete(testId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Student submits test
    @PostMapping(value = ["student-test"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @PreAuthorize("hasAuthority('STUDENT')")
    fun studentSubmitsTest(@RequestBody testResultDto: TestResultDto): ResponseEntity<TestResultDto> {
        val testResult = testService.studentSubmitsTest(testResultDto)
        return ResponseEntity(TestResultDto(testResult.id!!, Date(), testResult.student.id!!, ArrayList()), HttpStatus.OK)
    }

    @GetMapping(value = ["{testId}"])
    fun getTest(@PathVariable("testId") testId: Long): ResponseEntity<TestDTO> {
        val test = testService.findOneById(testId)
        val dto = TestDTO(test)
        dto.questions.forEach {
            it.answer.forEach {
                it.correct = false
            }
        }
        return ResponseEntity(dto, HttpStatus.OK)
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
