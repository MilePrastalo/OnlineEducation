package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Grade

interface GradeRepository : JpaRepository<Grade, Long> {
}
