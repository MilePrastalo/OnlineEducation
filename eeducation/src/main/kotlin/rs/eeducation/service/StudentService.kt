package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.Student
import rs.eeducation.repository.StudentRepository
import javax.persistence.EntityNotFoundException

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun save(student: Student): Student {
        return studentRepository.save(student)
    }

    fun findById(studentId: Long): Student {
        return studentRepository.findById(studentId).orElseThrow { EntityNotFoundException("Student not found") }
    }

    fun findByEmail(email: String): Student {
        return studentRepository.findByEmail(email).orElseThrow { EntityNotFoundException("Student not found") }
    }

}
