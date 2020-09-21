package rs.eeducation.model

import javax.persistence.*

@Entity
class UserAnswer(@Id
                 @GeneratedValue
                 var id: Long?,
                 @ManyToOne
                 var answer: Answer?,
                 @Column
                 var text: String) {
}
