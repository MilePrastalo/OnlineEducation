package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.SchoolDetailsDto
import rs.eeducation.dto.SchoolDto
import rs.eeducation.dto.UserBasicDto
import rs.eeducation.service.SchoolService

@RestController
@RequestMapping(value = ["api/school"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class SchoolController(private val schoolService: SchoolService) {

    //Teacher sends request to join school
    @PostMapping(value = ["teacher_request/{schoolId}"])
    fun askToJoinSchoolTeacher(@PathVariable("schoolId") schoolId: Long): ResponseEntity<Unit> {
        schoolService.teacherSendsRequest(schoolId)
        return ResponseEntity(HttpStatus.OK)
    }

    //School views Teacher requests
    @GetMapping(value = ["teacher_request"])
    fun viewTeacherRequests(): ResponseEntity<List<UserBasicDto>> {
        val teachers = schoolService.viewTeacherRequests()
        val dto = teachers.map { teacher -> UserBasicDto(teacher.id, teacher.email, teacher.name) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Accept Teacher join request
    @PutMapping(value = ["teacher_request/{teacherId}"])
    fun acceptTeacherRequest(@PathVariable("teacherId") teacherId: Long): ResponseEntity<Unit> {
        val school = schoolService.acceptTeacherRequest(teacherId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Reject Teacher join request
    @DeleteMapping(value = ["teacher_request/{teacherId}"])
    fun rejectTeacherRequest(@PathVariable("teacherId") teacherId: Long): ResponseEntity<Unit> {
        val school = schoolService.rejectTeacherRequest(teacherId)
        return ResponseEntity(HttpStatus.OK)
    }

    //School invite teacher to join school
    @PostMapping(value = ["invite_teacher/{teacherEmail}"])
    fun inviteTeacher(@PathVariable("teacherEmail") teacherEmail: String): ResponseEntity<Unit> {
        schoolService.inviteTeacher(teacherEmail)
        return ResponseEntity(HttpStatus.OK)
    }

    //Student requests to join school
    //For schools that require approval
    @PostMapping(value = ["student_request/{schoolId}"])
    fun askToJoinSchoolStudent(@PathVariable("schoolId") schoolId: Long): ResponseEntity<Unit> {
        schoolService.studentSendsRequest(schoolId)
        return ResponseEntity(HttpStatus.OK)
    }

    //School view Student requests
    @GetMapping(value = ["student_request"])
    fun viewStudentRequests(): ResponseEntity<List<UserBasicDto>> {
        val students = schoolService.viewStudentRequests()
        val dto = students.map { student -> UserBasicDto(student.id, student.email, student.name) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Accept student request
    @PutMapping(value = ["student_request/{studentId}"])
    fun acceptStudentRequest(@PathVariable("studentId") studentId: Long): ResponseEntity<Unit> {
        schoolService.acceptStudentRequest(studentId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Reject student request
    @DeleteMapping(value = ["student_request/{studentId}"])
    fun rejectStudentRequest(@PathVariable("studentId") studentId: Long): ResponseEntity<Unit> {
        schoolService.rejectStudentRequest(studentId)
        return ResponseEntity(HttpStatus.OK)
    }

    // Invite student to join school
    @PostMapping(value = ["invite_student/{studentEmail}"])
    fun inviteStudent(@PathVariable("studentEmail") studentEmail: String): ResponseEntity<Unit> {
        schoolService.inviteStudent(studentEmail)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping(value = ["invitation/{schoolId}"])
    fun acceptSchoolInvitation(@PathVariable("schoolId") schoolId: Long): ResponseEntity<Unit> {
        schoolService.acceptInvitation(schoolId)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping(value = ["invitation/{schoolId}"])
    fun rejectSchoolInvitation(@PathVariable("schoolId") schoolId: Long): ResponseEntity<Unit> {
        schoolService.rejectInvitation(schoolId)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping(value = ["schoolInvitations"])
    fun getSchoolInvitations(): ResponseEntity<List<SchoolDto>> {
        val schools = schoolService.viewInvitations()
        val dto = schools.map { school -> SchoolDto(school) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    @GetMapping(value = ["{schoolId}"])
    fun getSchool(@PathVariable("schoolId") schoolId: Long): ResponseEntity<SchoolDetailsDto> {
        val school = schoolService.findById(schoolId)
        val dto = SchoolDetailsDto(school)
        return ResponseEntity(dto, HttpStatus.OK)
    }

    @GetMapping(value = [""])
    fun getSchoolSelf(): ResponseEntity<SchoolDetailsDto> {
        val school = schoolService.getSchoolSelf()
        val dto = SchoolDetailsDto(school)
        return ResponseEntity(dto, HttpStatus.OK)
    }
}
