package rs.eeducation.model

import java.util.*
import javax.persistence.*

@Entity
class Test(@Id
           @GeneratedValue
           var id: Long?,
           @Column
           var name: String,
           @Column
           var date: Date,
           @Column
           var duration: Int,
           @Enumerated(EnumType.STRING)
           var testType: TestType,
           @OneToMany
           var questions: Set<Question>,
           @ManyToOne
           var course: Course) {
}
