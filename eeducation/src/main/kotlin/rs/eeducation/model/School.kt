package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class School(id: Long?, email: String, password: String, roles: Collection<Role>, confirmed: Boolean,
             @OneToMany
             var courses: Set<Course>,
             @ManyToMany
             var teachers: Set<Teacher>
) : User(id, email, password, roles, confirmed) {
}
