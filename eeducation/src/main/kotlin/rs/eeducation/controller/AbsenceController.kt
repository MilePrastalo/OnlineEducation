package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rs.eeducation.dto.AbsenceDTO
import rs.eeducation.dto.AddAbsenceDTO
import rs.eeducation.service.AbsenceService

@RestController
@RequestMapping(value = ["/api/absences"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class AbsenceController(private val absenceService: AbsenceService) {

    //Add absence
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addAbsence(@RequestBody absenceDTO: AddAbsenceDTO): ResponseEntity<AbsenceDTO> {
        val absence = absenceService.createAbsence(absenceDTO)
        return ResponseEntity(AbsenceDTO(absence), HttpStatus.OK)
    }

    //Student views his absences
    @GetMapping()
    fun viewAbsencesOfStudent(): ResponseEntity<List<AbsenceDTO>> {
        val absences = absenceService.viewAbsenceOfStudent()
        val listDto = absences.map { absence -> AbsenceDTO(absence) }
        return ResponseEntity(listDto, HttpStatus.OK)
    }


    //View absences of students in class
    @GetMapping(value = ["{courseId}"])
    fun viewAbsencesInCourse(@PathVariable("courseId") courseId: Long): ResponseEntity<List<AbsenceDTO>> {
        val absences = absenceService.viewAbsenceOfClass(courseId)
        val listDto = absences.map { absence -> AbsenceDTO(absence) }
        return ResponseEntity(listDto, HttpStatus.OK)

    }

}
