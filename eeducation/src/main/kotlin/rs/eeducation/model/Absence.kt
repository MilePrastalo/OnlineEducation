package rs.eeducation.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Absence(@Id
              @GeneratedValue
              var Id: Long?,
              @ManyToOne
              var course: Course,
              @ManyToOne
              var lection: Lection,
              @ManyToOne
              var student: Student) {


}
