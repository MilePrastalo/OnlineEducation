package rs.eeducation.model

import javax.persistence.*

@Entity
class UserQuestionResult(@Id
                         @GeneratedValue
                         var id: Long?,
                         @ManyToOne
                         var question: Question,
                         @OneToMany
                         var userAnswer: Set<UserAnswer>,
                         @Column
                         var points: Int?) {
}
