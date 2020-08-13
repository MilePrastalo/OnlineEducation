package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.School
import rs.eeducation.model.Student
import rs.eeducation.model.Teacher
import rs.eeducation.repository.SchoolRepository
import javax.persistence.EntityNotFoundException

@Service
class SchoolService(private val schoolRepository: SchoolRepository,
                    private val userService: UserService,
                    private val teacherService: TeacherService,
                    private val studentService: StudentService) {

    fun save(school: School): School {
        return schoolRepository.save(school)
    }

    fun findById(schoolId: Long): School {
        return schoolRepository.findById(schoolId).orElseThrow { EntityNotFoundException() }
    }

    fun teacherSendsRequest(schoolId: Long) {
        val school = findById(schoolId)
        val teacher = userService.getLoggedInUser() as Teacher
        (school.teacherRequest as MutableSet).add(teacher)
        save(school)
    }

    fun viewTeacherRequests(): List<Teacher> {
        val school = userService.getLoggedInUser() as School
        return school.teacherRequest.toList()
    }

    fun acceptTeacherRequest(teacherId: Long): School {
        val school = userService.getLoggedInUser() as School
        val teacher = school.teacherRequest.find { teacher -> teacher.id == teacherId }
                ?: throw Exception("Teacher does not exist")
        (school.teacherRequest as MutableSet).remove(teacher)
        (school.teachers as MutableSet).add(teacher)
        (teacher.schools as MutableSet).add(school)
        teacherService.save(teacher)
        return save(school)
    }

    fun rejectTeacherRequest(teacherId: Long): School {
        val school = userService.getLoggedInUser() as School
        val teacher = school.teacherRequest.find { teacher -> teacher.id == teacherId }
                ?: throw Exception("Teacher does not exist")
        (school.teacherRequest as MutableSet).remove(teacher)
        return save(school)
    }

    fun inviteTeacher(teacherId: Long) {
        val school = userService.getLoggedInUser() as School
        val teacher = teacherService.findById(teacherId)
        (teacher.invitedToSchool as MutableSet).add(school)
        teacherService.save(teacher)
    }

    fun studentSendsRequest(schoolId: Long) {
        val school = findById(schoolId)
        val student = userService.getLoggedInUser() as Student
        (school.studentRequest as MutableSet).add(student)
        save(school)
    }

    fun viewStudentRequests(): List<Student> {
        val school = userService.getLoggedInUser() as School
        return school.studentRequest.toList()
    }

    fun acceptStudentRequest(studentId: Long): School {
        val school = userService.getLoggedInUser() as School
        val student = school.studentRequest.find { student -> student.id == studentId }
                ?: throw Exception("Student does not exist")
        (school.studentRequest as MutableSet).remove(student)
        (school.students as MutableSet).add(student)
        (student.schools as MutableSet).add(school)
        studentService.save(student)
        return save(school)
    }

    fun rejectStudentRequest(studentId: Long): School {
        val school = userService.getLoggedInUser() as School
        val student = school.studentRequest.find { student -> student.id == studentId }
                ?: throw Exception("Student does not exist")
        (school.studentRequest as MutableSet).remove(student)
        return save(school)
    }

    fun inviteStudent(studentId: Long) {
        val school = userService.getLoggedInUser() as School
        val student = studentService.findById(studentId)
        (student.schoolsInvited as MutableSet).add(school)
        studentService.save(student)
    }

    fun acceptInvitation(schoolId: Long) {
        val school = findById(schoolId)
        val user = userService.getLoggedInUser()
        when (user) {
            is Teacher -> {
                (user.invitedToSchool as MutableSet).remove(school)
                (user.schools as MutableSet).add(school)
                (school.teachers as MutableSet).add(user)
                teacherService.save(user)
            }
            is Student -> {
                (user.schoolsInvited as MutableSet).remove(school)
                (user.schools as MutableSet).add(school)
                (school.students as MutableSet).add(user)
                studentService.save(user)
            }
        }
        save(school)
    }

    fun rejectInvitation(schoolId: Long) {
        val school = findById(schoolId)
        when (val user = userService.getLoggedInUser()) {
            is Teacher -> {
                (user.invitedToSchool as MutableSet).remove(school)
                teacherService.save(user)
            }
            is Student -> {
                (user.schoolsInvited as MutableSet).remove(school)
                studentService.save(user)
            }
        }
    }
}
