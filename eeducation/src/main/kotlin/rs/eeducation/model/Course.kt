package rs.eeducation.model

import java.util.*
import javax.persistence.*

@Entity
class Course(@Id
             @GeneratedValue
             var id: Long?,
             @OneToMany
             var lessons: Set<Lesson>,
             @ManyToMany(mappedBy = "courses")
             var students: Set<Student>,
             @ManyToMany(mappedBy = "courses")
             var teachers: Set<Teacher>,
             @ManyToOne
             var school: School?,
             @OneToMany
             var grades: Set<Grade>,
             @OneToMany
             var tests: Set<Test>,
             @OneToMany
             var absences: Set<Absence>,
             @Column
             var name: String,
             @Column
             var beginsDate: Date,
             @Column
             var endsDate: Date,
             @Column
             var isArchivated: Boolean,
             @Column
             var freeToJoin: Boolean,
             @OneToMany
             var studentsWaiting: Set<Student>,
             @Column
             var descr: String) {

}
