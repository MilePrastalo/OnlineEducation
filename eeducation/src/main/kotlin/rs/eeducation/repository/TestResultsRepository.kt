package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Test
import rs.eeducation.model.TestResults

interface TestResultsRepository : JpaRepository<TestResults, Long> {
    fun findByTest(test: Test): List<TestResults>
}
