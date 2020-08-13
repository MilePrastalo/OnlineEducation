package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.AddGradeDto
import rs.eeducation.model.Grade
import rs.eeducation.model.Student
import rs.eeducation.repository.GradeRepository

@Service
class GradeService(private val gradeRepository: GradeRepository,
                   private val studentService: StudentService,
                   private val courseService: CourseService,
                   private val userService: UserService) {

    fun addGrade(addGradeDto: AddGradeDto): Grade {
        val student = studentService.findById(addGradeDto.studentId)
        val course = courseService.findById(addGradeDto.courseId)
        userService.teacherTeacherCourse(userService.getLoggedInUser()!!, course)
        val grade = Grade(null, student, course, addGradeDto.grade)
        return save(grade)
    }

    fun save(grade: Grade): Grade {
        return gradeRepository.save(grade)
    }

    fun studentGrades(): List<Grade> {
        val student = userService.getLoggedInUser() as Student
        return gradeRepository.findAllByStudent(student)
    }

    fun courseGrades(courseId: Long): List<Grade> {
        val course = courseService.findById(courseId)
        val teacher = userService.getLoggedInUser()
        userService.teacherTeacherCourse(teacher!!, course)
        return gradeRepository.findAllByCourse(course)
    }

}
