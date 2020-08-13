package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.AddAbsenceDTO
import rs.eeducation.model.Absence
import rs.eeducation.model.School
import rs.eeducation.model.Student
import rs.eeducation.model.Teacher
import rs.eeducation.repository.AbsenceRepository
import javax.persistence.EntityNotFoundException

@Service
class AbsenceService(private val absenceRepository: AbsenceRepository,
                     private val lessonService: LessonService,
                     private val courseService: CourseService,
                     private val studentService: StudentService,
                     private val userService: UserService) {

    fun findById(absenceId: Long): Absence {
        return absenceRepository.findById(absenceId).orElseThrow { EntityNotFoundException("Absence does not exist") }
    }

    fun save(absence: Absence): Absence {
        return absenceRepository.save(absence)
    }

    fun createAbsence(absenceDTO: AddAbsenceDTO): Absence {
        //Get student, course and lesson
        val student = studentService.findById(absenceDTO.studentId)
        val lesson = lessonService.findById(absenceDTO.lessonId)
        val course = lesson.course

        //Check if student is taking this course
        val existing = course.students.find { studentCourse -> studentCourse.id == student.id }
                ?: throw Exception("Student is not taking course")

        //Check if logged in person is teaching this course
        val loggedInUser = userService.getLoggedInUser()
        when (loggedInUser) {
            is Teacher -> {
                loggedInUser.courses.find { courseTeacher -> courseTeacher.id == course.id }
                        ?: throw Exception("Teacher is not teaching course")
            }
            is School -> {
                if (course.school?.id != loggedInUser.id) {
                    throw java.lang.Exception("School is not in charge of that course")
                }
            }
        }

        var absence = Absence(null, course, lesson, student)
        absence = absenceRepository.save(absence)
        //Add to remaining lists
        (course.absences as MutableSet).add(absence)
        (lesson.absences as MutableSet).add(absence)
        (student.absences as MutableSet).add(absence)
        courseService.save(course)
        lessonService.save(lesson)
        studentService.save(student)
        return absence
    }

    fun viewAbsenceOfStudent(): List<Absence> {
        val student = userService.getLoggedInUser()
        if (student !is Student) {
            throw Exception("User is not student")
        } else {
            return student.absences.toList()
        }
    }

    fun viewAbsenceOfClass(courseId: Long): List<Absence> {
        val course = courseService.findById(courseId)
        val user = userService.getLoggedInUser()
        // Check if user is teaching this course or if it is a school that owns course
        when (user) {
            is Teacher -> {
                user.courses.find { courseTeacher -> courseTeacher.id == courseId }
                        ?: throw Exception("You do not have acces to this courses absences")
            }
            is School -> {
                if (user.id != course.school?.id) {
                    throw Exception("You do not have acces to this courses absences")
                }
            }
        }
        return course.absences.toList()
    }
}
