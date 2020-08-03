package rs.eeducation.model

import java.util.*
import javax.persistence.*

@Entity
class Course(@Id
             @GeneratedValue
             var id: Long?,
             @OneToMany
             var lections: Set<Lection>,
             @OneToMany
             var students: Set<Student>,
             @OneToMany
             var teachers: Set<Teacher>,
             @ManyToOne
             var school: School,
             @OneToMany
             var grades: Set<Grade>,
             @OneToMany
             var tests: Set<Test>,
             @OneToMany
             var absences: Set<Absence>,
             @Column
             var name: String,
             @Column
             var begins: Date,
             @Column
             var ends: Date,
             @Column
             var archivated: Boolean) {

}
