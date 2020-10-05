package rs.eeducation.model

import org.springframework.lang.Nullable
import javax.persistence.*

@Entity
class Answer(@Id
             @GeneratedValue
             var id: Long?,
             @Column
             var answerText: String,
             @Column
             var correct: Boolean?,
             @Enumerated(EnumType.STRING)
             var answerType: ANSWER_TYPE?) {
}
