package rs.eeducation.repository

import org.springframework.data.jpa.repository.JpaRepository
import rs.eeducation.model.Absence

interface AbsenceRepository : JpaRepository<Absence, Long> {
}
