package rs.eeducation.model

import javax.persistence.*

@Entity
class Question(@Id
               @GeneratedValue
               var id: Long?,
               @Column
               var name: String,
               @Enumerated(EnumType.STRING)
               var questionType: QuestionType,
               @OneToMany
               var answer: Set<Answer>) {
}
