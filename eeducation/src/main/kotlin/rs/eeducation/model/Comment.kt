package rs.eeducation.model

import java.util.*
import javax.persistence.*

@Entity
class Comment(@Id
              @GeneratedValue
              var id: Long,
              @ManyToOne
              var user: User,
              @OneToMany
              var replies: Set<Comment>,
              @Column
              var datePosted: Date) {
}
