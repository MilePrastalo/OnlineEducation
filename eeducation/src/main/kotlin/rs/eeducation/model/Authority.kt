package rs.eeducation.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*


@Entity
class Authority() : GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column()
    var name: String? = null

    fun Authority() {
        // TODO Auto-generated constructor stub
    }

    constructor(id: Long?, name: String?):this() {
        this.id = id
        this.name = name
    }

    override fun getAuthority(): String? {
        return name
    }

}
