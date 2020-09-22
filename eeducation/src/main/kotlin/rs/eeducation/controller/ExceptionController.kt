package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import rs.eeducation.dto.ErrorDetailsDto
import rs.eeducation.exceptions.UnAuthorizedException
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ExceptionController {
    @ExceptionHandler(value = [(EntityNotFoundException::class)])
    fun handleException(e: Exception): ResponseEntity<ErrorDetailsDto> {
        return ResponseEntity(ErrorDetailsDto(e.message!!),HttpStatus.NOT_FOUND)
    }
    @ExceptionHandler(value = [(UnAuthorizedException::class)])
    fun handleException2(e: Exception): ResponseEntity<ErrorDetailsDto> {
        return ResponseEntity(ErrorDetailsDto(e.message!!),HttpStatus.FORBIDDEN)
    }
}
