package rs.eeducation.dto

import rs.eeducation.model.UserType

class RegistrationRequest(val name: String,
                          val email: String,
                          val password: String,
                          val confirmedPassword: String,
                          val country: String,
                          val userType: UserType) {
}
