package rs.eeducation.model

import javax.persistence.*

@Entity
class Role(
        @Id
        @GeneratedValue
        val id: Long,

        @Column(unique = true)
        val name: String,

        @ManyToMany(mappedBy = "roles")
        val users: Collection<User>?,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "roles_privileges", joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")])
        val privileges: Collection<Privilege>
) {


}
