package rs.eeducation.model

import javax.persistence.*

@Entity
class Grade(@Id
            @GeneratedValue
            var id: Long?,
            @ManyToOne
            var student: Student,
            @ManyToOne
            var course: Course,
            @Column
            var value: String) {
}
