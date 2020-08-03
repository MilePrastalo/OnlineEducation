package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.dto.CreateCourseDTO
import rs.eeducation.dto.EditCourseDTO
import rs.eeducation.exceptions.UnAuthorizedException
import rs.eeducation.model.Course
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

    fun createCourse(createCourseDTO: CreateCourseDTO): Course {
        val teachers = HashSet<Teacher>()
        for (teacher in createCourseDTO.teachers) {
            teachers.add(teacherService.findByEmail(teacher))
        }
        val school = schoolService.findById(createCourseDTO.school)
        val sdf = SimpleDateFormat("MM-dd-yyyy")
        val course = Course(null, HashSet(), HashSet(), teachers, school, HashSet(), HashSet(), HashSet(),
                createCourseDTO.name, sdf.parse(createCourseDTO.begins), sdf.parse(createCourseDTO.ends), false)
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
        if (!(user in course.teachers || user?.email == course.school.email)) {
            throw UnAuthorizedException("You dont have permission to edit this course")
        }
        //edit course
        val sdf = SimpleDateFormat("MM-dd-yyyy")
        course.name = editCourseDTO.name
        course.begins = sdf.parse(editCourseDTO.begins)
        course.ends = sdf.parse(editCourseDTO.ends)
        //save course
        //return course
        return courseRepository.save(course)
    }

    fun archiveCourse(courseId: Long): Course {
        val course = courseRepository.findById(courseId).orElseThrow { EntityNotFoundException("Course not exist") }
        course.archivated = true
        return courseRepository.save(course)
    }
}

