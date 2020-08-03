package rs.eeducation.dto

import rs.eeducation.model.Test

class TestDTO {
    var id: Long?
    var name: String

    constructor(test: Test) {
        id = test.id
        name = test.name
    }
}
