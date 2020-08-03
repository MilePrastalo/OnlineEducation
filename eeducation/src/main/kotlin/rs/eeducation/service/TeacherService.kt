package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.Teacher
import rs.eeducation.repository.TeacherRepository
import javax.persistence.EntityNotFoundException

@Service
class TeacherService(private val teacherRepository: TeacherRepository) {

    fun save(teacher: Teacher): Teacher {
        return teacherRepository.save(teacher)
    }

    fun findById(teacherId: Long): Teacher {
        return teacherRepository.findById(teacherId).orElseThrow { EntityNotFoundException("Teacher not exists") }
    }

    fun findByEmail(email: String): Teacher {
        return teacherRepository.findByEmail(email).orElseThrow { EntityNotFoundException("Teacher not exist") }
    }
}
