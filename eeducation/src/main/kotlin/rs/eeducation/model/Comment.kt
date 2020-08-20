package rs.eeducation.model

import java.util.*
import javax.persistence.*

@Entity
class Comment(@Id
              @GeneratedValue
              var id: Long?,
              @ManyToOne
              var user: User,
              @OneToMany
              var replies: Set<Comment>,
              @Column
              var text: String,
              @Column
              var datePosted: Date,
              @Column
              var reply: Boolean) {
}
