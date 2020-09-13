package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.Course
import rs.eeducation.model.School

@Service
class SearchService(private val courseService: CourseService, private val schoolService: SchoolService) {

    fun searchSchools(name: String): List<School> {
        return schoolService.findSchoolsByName(name)
    }

    fun searchCourses(name: String): List<Course> {
        return courseService.findByName(name)
    }
}
