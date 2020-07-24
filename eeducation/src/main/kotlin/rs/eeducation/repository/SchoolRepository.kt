package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.School

interface SchoolRepository : JpaRepository<School, Long> {
}
