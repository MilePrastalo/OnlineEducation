package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.coverters.QuestionConverter
import rs.eeducation.dto.CreateTestDto
import rs.eeducation.dto.TestResultDto
import rs.eeducation.model.*
import rs.eeducation.repository.*
import javax.persistence.EntityNotFoundException

@Service
class TestService(private val testRepository: TestRepository,
                  private val userService: UserService,
                  private val courseService: CourseService,
                  private val questionRepository: QuestionRepository,
                  private val answerRepository: AnswerRepository,
                  private val userQuestionResultRepository: UserQuestionResultRepository,
                  private val userAnswerRepository: UserAnswerRepository,
                  private val testResultsRepository: TestResultsRepository) {

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
        val savedQuestions = HashSet<Question>();
        for (question in questions) {
            val answers = question.answer.map { answer -> answerRepository.save(answer) }.toSet()
            question.answer = answers
            savedQuestions.add(questionRepository.save(question))
        }
        var test = Test(null, createTestDto.name, createTestDto.date, createTestDto.duration, createTestDto.testType, savedQuestions, course)
        test = save(test)
        (course.tests as MutableSet).add(test)
        courseService.save(course)
        return test
    }

    fun studentSubmitsTest(testResultDto: TestResultDto): TestResults {
        val student = userService.getLoggedInUser() as Student
        val test = findOneById(testResultDto.testId)
        val userQuestionResults = HashSet<UserQuestionResult>()
        for (question in testResultDto.userQuestionResults) {
            val answers = HashSet<UserAnswer>()
            for (answer in question.answers) {
                val userAnswer = if (answer.id != null) {
                    UserAnswer(null, answerRepository.findById(answer.id!!).get(), answer.answerText)
                } else {
                    UserAnswer(null, null, answer.answerText)
                }
                answers.add(userAnswerRepository.save(userAnswer))
            }
            val userQuestionResult = UserQuestionResult(null, questionRepository.findById(question.questionId).get(), answers)
            userQuestionResults.add(userQuestionResultRepository.save(userQuestionResult))
        }
        val testResults = TestResults(null, student, test, userQuestionResults)
        return testResultsRepository.save(testResults)
    }

}
