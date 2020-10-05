package rs.eeducation.service

import net.ricecode.similarity.JaroWinklerStrategy
import net.ricecode.similarity.SimilarityStrategy
import net.ricecode.similarity.StringSimilarityService
import net.ricecode.similarity.StringSimilarityServiceImpl
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
            val userQuestionResult = UserQuestionResult(null, questionRepository.findById(question.questionId).get(), answers, 0)
            userQuestionResults.add(userQuestionResultRepository.save(userQuestionResult))
        }
        val testResults = testResultsRepository.save(TestResults(null, student, test, userQuestionResults))
        if (test.testType == TestType.SELF_GRADING) {
            gradeTest(testResults)
        }
        return testResults
    }

    fun getTestResults(testId: Long): List<TestResults> {
        val test = findOneById(testId)
        return testResultsRepository.findByTest(test)
    }

    fun gradeTest(testResults: TestResults): String {
        val test = testResults.test
        val testQuestions = test.questions
        val userQuestionResult = testResults.userQuestionResults
        val totalPoints = testQuestions.sumBy { question -> question.questionPoints }
        var userPoints = 0
        for (questionResult in userQuestionResult) {
            val question = questionResult.question
            val questionPoints = gradeQuestion(question, questionResult.userAnswer.toList())
            questionResult.points = questionPoints
            userPoints += questionPoints
        }
        return "$userPoints/$totalPoints"

    }

    private fun gradeQuestion(question: Question, userAnswers: List<UserAnswer>): Int {
        if (question.questionType == QuestionType.CHECKBOXES) {
            userAnswers.forEach { userAnswer ->
                var failed = false
                question.answer.forEach { answer ->
                    if (answer.id == userAnswer.answer?.id) {
                        if (!answer.correct!!) {
                            failed = true;
                        }
                    }
                }
                val numOfCorrectAnswers = question.answer.filter { answer -> answer.correct!! }
                if (userAnswers.size != numOfCorrectAnswers.size) {
                    failed = true
                }
                if (!failed) {
                    return question.questionPoints
                }
            }
        } else if (question.questionType == QuestionType.MULTIPLE_CHOICE) {
            if (userAnswers.isNotEmpty()) {
                val userAnswer = userAnswers[0]
                val answer = question.answer.filter { answer -> answer.id!! == userAnswer.answer!!.id }
                if (answer[0].correct!!) {
                    return question.questionPoints
                }
            }
        } else if (question.questionType == QuestionType.SHORT_ANSWER) {
            if (userAnswers.isNotEmpty()) {
                val answer = question.answer.toList()[0]
                if (answer.answerType == ANSWER_TYPE.PERFECT_MATCH) {
                    val text = answer.answerText
                    val userAnswerText = userAnswers[0].text
                    if (text == userAnswerText) {
                        return question.questionPoints
                    }
                } else if (answer.answerType == ANSWER_TYPE.IGNORE_CASING) {
                    val text = answer.answerText.toLowerCase()
                    val userAnswerText = userAnswers[0].text.toLowerCase()
                    if (text == userAnswerText) {
                        return question.questionPoints
                    }
                } else {
                    val strategy: SimilarityStrategy = JaroWinklerStrategy()
                    val target = question.answer.toList()[0].answerText
                    val source = userAnswers[0].text
                    val service: StringSimilarityService = StringSimilarityServiceImpl(strategy)
                    val score = service.score(source, target)
                    if (score >= 0.95) {
                        return question.questionPoints
                    }
                }
            }



        }
        return 0
    }

    fun viewStudentTestAnswer(testResultId: Long): TestResults {
        val testResults = testResultsRepository.findById(testResultId).get()
        for (question in testResults.userQuestionResults) {
            question.points = gradeQuestion(question.question, question.userAnswer.toList())
        }
        return testResults
    }

}
