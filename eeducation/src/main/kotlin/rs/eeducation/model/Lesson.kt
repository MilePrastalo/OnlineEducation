package rs.eeducation.model

import java.util.*
import javax.persistence.*

@Entity
class Lesson(@Id
              @GeneratedValue
              var id: Long?,
             @ManyToOne
              var course: Course,
             @OneToMany
              var absences: Set<Absence>,
             @OneToMany
              var comments: Set<Comment>,
             @Column
              var pathToFile: String,
             @Column
              var name: String,
             @Column
              var date: Date
) {
}
