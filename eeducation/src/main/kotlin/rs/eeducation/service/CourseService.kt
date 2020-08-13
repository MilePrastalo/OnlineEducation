package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.CreateCourseDTO
import rs.eeducation.dto.EditCourseDTO
import rs.eeducation.model.Course
import rs.eeducation.model.School
import rs.eeducation.model.Student
import rs.eeducation.model.Teacher
import rs.eeducation.repository.CourseRepository
import java.text.SimpleDateFormat
import javax.persistence.EntityNotFoundException

@Service
class CourseService(private val courseRepository: CourseRepository,
                    private val teacherService: TeacherService,
                    private val schoolService: SchoolService,
                    private val userService: UserService) {

    fun getTeacherCourses(teacherId: Long): List<Course> {
        val teacher = teacherService.findById(teacherId)
        return courseRepository.findByTeachersContaining(teacher)
    }

    fun findById(courseId: Long): Course {
        return courseRepository.findById(courseId).orElseThrow { EntityNotFoundException("Course does not exist") }
    }

    fun save(course: Course): Course {
        return courseRepository.save(course)
    }

    fun createCourse(createCourseDTO: CreateCourseDTO): Course {
        val teachers = HashSet<Teacher>()
        for (teacher in createCourseDTO.teachers) {
            teachers.add(teacherService.findByEmail(teacher))
        }
        var school: School? = null
        if (createCourseDTO.school != -1L) {
            school = schoolService.findById(createCourseDTO.school)
        }
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val course = Course(null, HashSet(), HashSet(), teachers, school, HashSet(), HashSet(), HashSet(),
                createCourseDTO.name, createCourseDTO.begins, createCourseDTO.ends, false, createCourseDTO.freeToJoin, HashSet(), createCourseDTO.description)
        val savedCourse = courseRepository.save(course)
        for (teacher in teachers) {
            (teacher.courses as MutableSet).add(savedCourse)
            teacherService.save(teacher)
        }
        return savedCourse
    }

    fun editCourse(editCourseDTO: EditCourseDTO): Course {
        //Get course
        val course = courseRepository.findById(editCourseDTO.id).orElseThrow { EntityNotFoundException("Course does not exist") }
        //get logged in user
        val user = userService.getLoggedInUser()
        //Check if editor is school or teacher of said course if not throw exception
        userService.teacherTeacherCourse(user!!, course)
        //edit course
        course.name = editCourseDTO.name
        course.beginsDate = editCourseDTO.begins
        course.endsDate = editCourseDTO.ends
        course.descr = editCourseDTO.description
        //save course
        //return course
        return courseRepository.save(course)
    }

    fun archiveCourse(courseId: Long): Course {
        val course = courseRepository.findById(courseId).orElseThrow { EntityNotFoundException("Course not exist") }
        val user = userService.getLoggedInUser()
        userService.teacherTeacherCourse(user!!, course)
        course.isArchivated = true
        return courseRepository.save(course)
    }

    fun joinCourse(courseId: Long): Course {
        val student = userService.getLoggedInUser() as Student
        val course = findById(courseId)
        if (course.freeToJoin) {
            (course.students as MutableSet).add(student)
        } else {
            (course.studentsWaiting as MutableSet).add(student)
        }
        return save(course)
    }

    fun findStudentsOfClass(courseId: Long): List<Student> {
        val course = findById(courseId)
        return course.students.toList()
    }
}

