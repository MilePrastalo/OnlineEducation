package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.Teacher
import rs.eeducation.repository.TeacherRepository

@Service
class TeacherService(private val teacherRepository: TeacherRepository) {

    fun save(teacher: Teacher): Teacher {
        return teacherRepository.save(teacher)
    }
}
