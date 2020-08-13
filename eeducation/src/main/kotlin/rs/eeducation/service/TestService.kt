package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.coverters.QuestionConverter
import rs.eeducation.dto.CreateTestDto
import rs.eeducation.model.Test
import rs.eeducation.repository.TestRepository
import javax.persistence.EntityNotFoundException

@Service
class TestService(private val testRepository: TestRepository,
                  private val userService: UserService,
                  private val courseService: CourseService) {

    fun delete(testId: Long) {
        val test = findOneById(testId)
        val course = test.course
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        testRepository.deleteById(testId)
    }

    fun save(test: Test): Test {
        return testRepository.save(test)
    }

    fun findOneById(testId: Long): Test {
        return testRepository.findById(testId).orElseThrow { EntityNotFoundException("Test does not exist") }
    }

    fun createTest(createTestDto: CreateTestDto): Test {
        val course = courseService.findById(createTestDto.courseId)
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        val questions = createTestDto.questions.map { questionDto -> QuestionConverter.toQuestion(questionDto) }.toSet()
        val test = Test(null, createTestDto.name, createTestDto.date, createTestDto.duration, createTestDto.testType, questions, course)
        return save(test)
    }

}