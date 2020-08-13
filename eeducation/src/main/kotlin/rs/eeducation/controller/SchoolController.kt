package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.UserBasicDto
import rs.eeducation.service.SchoolService

@RestController
@RequestMapping(value = ["api/school"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class SchoolController(private val schoolService: SchoolService) {

    //Teacher sends request to join school
    @PostMapping(value = ["teacher_request/{schoolId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun askToJoinSchoolTeacher(@PathVariable("schoolId") schoolId: Long): ResponseEntity<Unit> {
        schoolService.teacherSendsRequest(schoolId)
        return ResponseEntity(HttpStatus.OK)
    }

    //School views Teacher requests
    @GetMapping(value = ["teacher_requests"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun viewTeacherRequests(): ResponseEntity<List<UserBasicDto>> {
        val teachers = schoolService.viewTeacherRequests()
        val dto = teachers.map { teacher -> UserBasicDto(teacher.id, teacher.email, teacher.name) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Accept Teacher join request
    @PutMapping(value = ["teacher_request/{teacherId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
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
    @PostMapping(value = ["invite_teacher/{teacherId}"])
    fun inviteTeacher(@PathVariable("teacherId") teacherId: Long): ResponseEntity<Unit> {
        schoolService.inviteTeacher(teacherId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Student requests to join school
    //For schools that require approval
    @PostMapping(value = ["student_request/{schoolId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun askToJoinSchoolStudent(@PathVariable("schoolId") schoolId: Long): ResponseEntity<Unit> {
        schoolService.studentSendsRequest(schoolId)
        return ResponseEntity(HttpStatus.OK)
    }

    //School view Student requests
    @GetMapping(value = ["student_request"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun viewStudentRequests(): ResponseEntity<List<UserBasicDto>> {
        val students = schoolService.viewStudentRequests()
        val dto = students.map { student -> UserBasicDto(student.id, student.email, student.name) }
        return ResponseEntity(dto, HttpStatus.OK)
    }

    //Accept student request
    @PutMapping(value = ["student_request/{studentId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun acceptStudentRequest(@PathVariable("studentId") studentId: Long): ResponseEntity<Unit> {
        schoolService.acceptStudentRequest(studentId)
        return ResponseEntity(HttpStatus.OK)
    }

    //Reject student request
    @DeleteMapping(value = ["teacher_request/{studentId}"])
    fun rejectStudentRequest(@PathVariable("studentId") studentId: Long): ResponseEntity<Unit> {
        schoolService.rejectStudentRequest(studentId)
        return ResponseEntity(HttpStatus.OK)
    }

    // Invite student to join school
    @PostMapping(value = ["invite_student/{studentId}"])
    fun inviteStudent(@PathVariable("studentId") studentId: Long): ResponseEntity<Unit> {
        schoolService.inviteStudent(studentId)
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
}
