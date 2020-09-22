package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class School(id: Long?, email: String, name: String, userPassword: String, authhority: Set<Authority>, confirmed: Boolean,
             @OneToMany
             var courses: Set<Course>,
             @ManyToMany(mappedBy = "schools")
             var teachers: Set<Teacher>,
             @ManyToMany(mappedBy = "schools")
             var students: Set<Student>,
             @ManyToMany
             var teacherRequest: Set<Teacher>,
             @ManyToMany
             var studentRequest: Set<Student>
) : User(id, email, name, userPassword, authhority, confirmed) {
}
