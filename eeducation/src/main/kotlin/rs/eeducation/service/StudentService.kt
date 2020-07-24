package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.Student
import rs.eeducation.repository.StudentRepository

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun save(student: Student): Student {
        return studentRepository.save(student)
    }
}
