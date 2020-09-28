package rs.eeducation.dto

import rs.eeducation.model.UserType

class RegistrationRequestDTO(val name: String,
                             val email: String,
                             val password: String,
                             val confirmedPassword: String,
                             val country: String,
                             val userType: UserType) {
}
