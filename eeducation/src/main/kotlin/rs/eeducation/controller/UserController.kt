package rs.eeducation.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rs.eeducation.dto.UserBasicDto
import rs.eeducation.service.UserService

@RestController
@RequestMapping(value = ["api/user"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin
class UserController(val userService: UserService) {

    @GetMapping()
    fun getUser(): ResponseEntity<UserBasicDto> {
        val user = userService.getLoggedInUser()!!
        val dto = UserBasicDto(user.id, user.email, user.name)
        return ResponseEntity(dto, HttpStatus.OK)
    }
}
