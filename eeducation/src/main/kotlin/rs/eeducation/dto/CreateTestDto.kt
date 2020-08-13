package rs.eeducation.dto

import rs.eeducation.model.TestType
import java.util.*

class CreateTestDto(
        var name: String,
        var date: Date,
        var duration: Int,
        var testType: TestType,
        var questions: List<QuestionDto>,
        var courseId: Long) {
}
