package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class UserQuestionResult(@Id
                         @GeneratedValue
                         var id: Long?,
                         @ManyToOne
                         var question: Question,
                         @ManyToOne
                         var userAnswer: UserAnswer) {
}
