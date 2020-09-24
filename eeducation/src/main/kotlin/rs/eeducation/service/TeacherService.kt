package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.Course
import rs.eeducation.model.Lesson
import rs.eeducation.model.School
import rs.eeducation.model.Teacher
import rs.eeducation.repository.TeacherRepository
import javax.persistence.EntityNotFoundException

@Service
class TeacherService(private val teacherRepository: TeacherRepository, private val userService: UserService) {

    fun save(teacher: Teacher): Teacher {
        return teacherRepository.save(teacher)
    }

    fun findById(teacherId: Long): Teacher {
        return teacherRepository.findById(teacherId).orElseThrow { EntityNotFoundException("Teacher not exists") }
    }

    fun findByEmail(email: String): Teacher {
        return teacherRepository.findByEmail(email).orElseThrow { EntityNotFoundException("Teacher not exist") }
    }

    fun getTeacherSchools(): List<School> {
        var teacher = userService.getLoggedInUser() as Teacher
        return teacher.schools.toList()
    }

    fun getTeacherLessons(): List<Lesson> {
        val teacher = userService.getLoggedInUser()
        if (teacher is Teacher) {
            val courses = teacher.courses
            val lessons = ArrayList<Lesson>()
            courses.forEach { course: Course ->
                course.lessons.forEach { lesson: Lesson ->
                    lessons.add(lesson)
                }
            }
            return lessons.filter { lesson -> lesson.lessonContentId != null && lesson.name != null }
        }
        return ArrayList()
    }
}
