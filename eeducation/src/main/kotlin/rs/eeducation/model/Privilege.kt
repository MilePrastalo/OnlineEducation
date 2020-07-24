package rs.eeducation.model

import javax.persistence.*


@Entity
class Privilege(@Id
                @GeneratedValue
                val id: Long,

                @Column(unique = true)
                val name: String,

                @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
                val roles: Collection<Role>) {


}
