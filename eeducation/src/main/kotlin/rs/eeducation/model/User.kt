package rs.eeducation.model

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class User(@Id
                @GeneratedValue
                var id: Long?,
                @Column
                var email: String,
                @Column
                var password: String,
                @ManyToMany(fetch = FetchType.EAGER)
                var roles: Collection<Role>,
                @Column
                var confirmed: Boolean
) {
}
