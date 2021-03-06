package rs.eeducation.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import rs.eeducation.dto.AuthenticationRequest
import rs.eeducation.dto.RegistrationRequestDTO
import rs.eeducation.exceptions.AccountNotActiveException
import rs.eeducation.jwt.JwtTokenUtil
import rs.eeducation.model.*
import rs.eeducation.repository.AuthorityRepository
import java.util.*
import kotlin.collections.HashSet

@Service
class AuthenticationService(private var authenticationManager: AuthenticationManager,
                            private var jwtUserDetailsService: JwtUserDetailsService,
                            private val jwtTokenUtil: JwtTokenUtil,
                            private val authorityRepository: AuthorityRepository,
                            private val schoolService: SchoolService,
                            private val teacherService: TeacherService,
                            private val studentService: StudentService,
                            private val userService: UserService,
                            private val emailService: EmailService) {

    fun login(authenticationRequest: AuthenticationRequest): String {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password))
        val userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.email)
        val user = userService.findByEmail(authenticationRequest.email)
        if (!user.confirmed) {
            throw AccountNotActiveException("Account is not activated. Check your email")
        }
        return jwtTokenUtil.generateToken(userDetails)
    }

    fun getUserByEmail(email:String):User{
        return userService.findByEmail(email)
    }

    fun register(registrationRequestDTO: RegistrationRequestDTO): String {
        val authorities = HashSet<Authority>()
        val bc = BCryptPasswordEncoder()
        return when (registrationRequestDTO.userType) {
            UserType.SCHOOL -> {
                authorities.add(authorityRepository.findByName("SCHOOL")!!)
                var school = School(null, registrationRequestDTO.email, bc.encode(registrationRequestDTO.password), registrationRequestDTO.name, authorities, false, HashSet(), HashSet(), HashSet(), HashSet(), HashSet())
                school = schoolService.save(school)
                emailService.sendRegistrationEmail(school)
                "School has been successfully registered"
            }
            UserType.TEACHER -> {
                authorities.add(authorityRepository.findByName("TEACHER")!!)
                var teacher = Teacher(null, registrationRequestDTO.email, bc.encode(registrationRequestDTO.password), registrationRequestDTO.name, authorities, false, HashSet(), HashSet(), HashSet())
                teacher = teacherService.save(teacher)
                emailService.sendRegistrationEmail(teacher)
                "Teacher has been successfully registered"
            }
            UserType.STUDENT -> {
                authorities.add(authorityRepository.findByName("STUDENT")!!)
                var student = Student(null, registrationRequestDTO.email, registrationRequestDTO.name, bc.encode(registrationRequestDTO.password), authorities, false, HashSet(), HashSet(), HashSet(), HashSet(), HashSet())
                student = studentService.save(student)
                emailService.sendRegistrationEmail(student)
                "Student has been successfully registered"
            }
        }
    }

    fun confirm(emailEncoded: String) {
        val decoded: ByteArray = Base64.getDecoder().decode(emailEncoded)
        val email = String(decoded, Charsets.UTF_8)
        println(email)
        val user: User = userService.findByEmail(email)
        user.confirmed = true
        userService.save(user)
    }
}
