package rs.eeducation.model

import org.springframework.lang.Nullable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Answer(@Id
             @GeneratedValue
             var id: Long?,
             @Column
             var answerText: String,
             @Column
             var correct: Boolean?) {
}
