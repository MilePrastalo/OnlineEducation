package rs.eeducation.model

import javax.persistence.*

@Entity
class TestResults(@Id
@GeneratedValue
var id:Long?,
@ManyToOne
var student:Student,
@ManyToOne
var test: Test,
@OneToMany
var userQuestionResults: Set<UserQuestionResult>) {
}
