package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Test

interface TestRepository : JpaRepository<Test, Long> {

}
