package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.ManyToMany

@Entity
class Teacher(id: Long?, email: String, name: String, userPassword: String, authority: Set<Authority>, confirmed: Boolean,
              @ManyToMany
              var schools: Set<School>,
              @ManyToMany
              var courses: Set<Course>,
              @ManyToMany
              var invitedToSchool: Set<School>) : User(id, email, name, userPassword, authority, confirmed) {
}
