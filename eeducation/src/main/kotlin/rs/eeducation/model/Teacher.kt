package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.ManyToMany

@Entity
class Teacher(id: Long?, email: String, name: String, password: String, roles: Collection<Role>, confirmed: Boolean,
              @ManyToMany
              var schools: Set<School>,
              @ManyToMany
              var courses: Set<Course>) : User(id, email, name, password, roles, confirmed) {
}
