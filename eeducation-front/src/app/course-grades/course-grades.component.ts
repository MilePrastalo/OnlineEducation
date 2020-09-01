import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {Router} from '@angular/router';
import {StudentGrade} from '../model/StudentGrade';

@Component({
  selector: 'app-course-grades',
  templateUrl: './course-grades.component.html',
  styleUrls: ['./course-grades.component.scss']
})
export class CourseGradesComponent implements OnInit {

  @Input() course: Course;
  grades: Array<StudentGrade>;
  displayedColumns: string[] = ['studentName', 'grades'];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.grades = this.createStudentGrades();
  }

  addGrade() {
    this.router.navigateByUrl('add-grade/' + this.course.id);
  }

  createStudentGrades(): Array<StudentGrade> {
    const studentId = new Array<number>();
    const gradeList = new Array<StudentGrade>();
    const gradesMap = new Map<number, StudentGrade>();
    for (const grade of this.course.grades) {
      if (!gradesMap.get(grade.studentId)) {
        studentId.push(grade.studentId);
        const studentGradeObject = new StudentGrade();
        gradeList.push(studentGradeObject);
        studentGradeObject.studentId = grade.studentId;
        studentGradeObject.studentName = grade.studentName;
        studentGradeObject.grades = new Array<string>();
        studentGradeObject.grades.push(grade.grade);
        gradesMap.set(grade.studentId, studentGradeObject);
      } else {
        const studentGradeObject = gradesMap.get(grade.studentId);
        studentGradeObject.grades.push(grade.grade);
      }
    }
    return gradeList;
  }

}
