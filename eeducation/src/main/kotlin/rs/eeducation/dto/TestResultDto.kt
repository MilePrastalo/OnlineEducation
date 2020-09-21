package rs.eeducation.dto

import java.util.*

class TestResultDto(val testId: Long,
                    val date: Date,
                    val studentId: Long,
                    var userQuestionResults: List<UserQuestionResultDto>) {


}
