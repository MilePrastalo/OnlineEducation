package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.School
import rs.eeducation.repository.SchoolRepository
import javax.persistence.EntityNotFoundException

@Service
class SchoolService(private val schoolRepository: SchoolRepository) {

    fun save(school: School): School {
        return schoolRepository.save(school)
    }

    fun findById(schoolId: Long): School {
        return schoolRepository.findById(schoolId).orElseThrow { EntityNotFoundException() }
    }
}
