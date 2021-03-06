package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import rs.eeducation.coverters.TestConverter
import rs.eeducation.dto.*
import rs.eeducation.model.TestType
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
        val score = if(testResult.test.testType == TestType.SELF_GRADING) testService.gradeTest(testResult) else ""
        return ResponseEntity(TestResultDto(testResult.id!!, Date(), testResult.student.id!!, ArrayList(), score), HttpStatus.OK)
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

    @GetMapping(value = ["testResults/{testId}"])
    fun viewTestResults(@PathVariable("testId") testId: Long): ResponseEntity<List<TestResultListDto>> {
        val testResults = testService.getTestResults(testId)
        val dto = testResults.map { testResults -> TestResultListDto(testResults) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //View students test answers
    @GetMapping(value = ["testResult/{testResultId}"])
    fun viewStudentTestAnswers(@PathVariable("testResultId") testResultId: Long): ResponseEntity<TestResultViewDto> {
        val testResult = testService.viewStudentTestAnswer(testResultId)
        val dto = TestResultViewDto(testResult)
        return ResponseEntity(dto, HttpStatus.OK)
    }
}
