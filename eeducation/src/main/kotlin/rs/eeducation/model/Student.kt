package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class Student(id: Long?, username: String, password: String, roles: Collection<Role>, confirmed: Boolean,
              @OneToMany
              var absences: Set<Absence>,
              @OneToMany
              var grades: Set<Grade>,
              @ManyToMany
              var courses: Set<Course>) : User(id, username, password, roles, confirmed) {
}
