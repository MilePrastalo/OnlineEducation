package rs.eeducation.service

import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import rs.eeducation.model.User
import java.io.UnsupportedEncodingException
import java.util.*


@Service
class EmailService(private var javaMailSender: JavaMailSender) {

    @Async
    @Throws(MailException::class, InterruptedException::class)
    fun sendRegistrationEmail(user: User) {
        System.out.println("Slanje emaila..." + user.email)
        val mail = SimpleMailMessage()
        mail.setTo(user.email)
        mail.setFrom("eeducation.global.isa@gmail.com")
        mail.setSubject("Eeducation: Account conformation")
        var url = ""
        try {
            url = Base64.getEncoder().encodeToString(user.email.toByteArray(Charsets.UTF_8))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        mail.setText("Go to: http://localhost:8080/auth/registrationConfirmation/$url to activate your account.")
        javaMailSender.send(mail)
        println("E-mail sent!")
    }
}
