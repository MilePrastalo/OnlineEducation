package rs.eeducation.service

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import rs.eeducation.exceptions.UnAuthorizedException
import rs.eeducation.model.Course
import rs.eeducation.model.User
import rs.eeducation.repository.UserRepository


@Service
class UserService(private var userRepository: UserRepository) {

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun findByEmail(email: String): User {
        return userRepository.findOneByEmail(email).get()
    }

    fun getLoggedInUser(): User? {
        var email = ""
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        email = if (authentication !is AnonymousAuthenticationToken) {
            authentication.name
        } else {
            return null
        }
        return findByEmail(email)
    }

    fun teacherTeacherCourse(user: User, course: Course){
        if (!(user in course.teachers || user?.email == course.school?.email)) {
            throw UnAuthorizedException("You dont have permission to edit this course")
        }
    }
}
