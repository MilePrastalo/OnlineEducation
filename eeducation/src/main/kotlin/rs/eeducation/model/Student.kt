package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class Student(id: Long?, email: String, name: String, password: String, roles: Collection<Role>, confirmed: Boolean,
              @OneToMany
              var absences: Set<Absence>,
              @OneToMany
              var grades: Set<Grade>,
              @ManyToMany
              var courses: Set<Course>,
              @ManyToMany
              var schoolsInvited: Set<School>,
              @ManyToMany
              var schools: Set<School>) : User(id, email, name, password, roles, confirmed) {
}
