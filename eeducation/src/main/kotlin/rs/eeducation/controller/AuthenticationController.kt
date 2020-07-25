package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import rs.eeducation.dto.AuthenticationRequest
import rs.eeducation.dto.AuthenticationResponse
import rs.eeducation.dto.RegistrationRequest
import rs.eeducation.dto.RegistrationResponse
import rs.eeducation.service.AuthenticationService


@RestController
@RequestMapping("auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @PostMapping(value = ["login"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody authenticationRequest: AuthenticationRequest):ResponseEntity<AuthenticationResponse>{
        val token = authenticationService.login(authenticationRequest)
        val authenticationResponse = AuthenticationResponse(token)
        return ResponseEntity(authenticationResponse,HttpStatus.OK)
    }

    @PostMapping(value = ["register"],consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@RequestBody registrationRequest: RegistrationRequest):ResponseEntity<RegistrationResponse>{
        val response = authenticationService.register(registrationRequest)
        return ResponseEntity(RegistrationResponse(response), HttpStatus.OK)
    }
    @GetMapping(value = ["registrationConfirmation/{emailEncoded}"])
    fun confirmAccount(@PathVariable("emailEncoded") emailEncoded:String):RedirectView {
        authenticationService.confirm(emailEncoded)
        return RedirectView("http://localhost:4200")
    }

}
