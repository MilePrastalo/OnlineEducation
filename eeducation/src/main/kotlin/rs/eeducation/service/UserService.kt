package rs.eeducation.service

import org.springframework.stereotype.Service
import rs.eeducation.model.User
import rs.eeducation.repository.UserRepository

@Service
class UserService(private var userRepository: UserRepository) {

    fun save(user:User):User{
        return userRepository.save(user)
    }
    fun findByEmail(email:String):User{
        return userRepository.findOneByEmail(email).get()
    }

}
