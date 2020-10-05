package rs.eeducation.dto

import rs.eeducation.model.TestResults

class TestResultListDto {
    var id: Long?
    var student: String

    constructor(testResults: TestResults) {
        id = testResults.id
        student = testResults.student.name
    }
}
