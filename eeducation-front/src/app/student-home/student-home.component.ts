import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CourseBasic} from '../model/CourseBasic';
import {StudentService} from '../service/student.service';
import {School} from '../model/School';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-student-home',
  templateUrl: './student-home.component.html',
  styleUrls: ['./student-home.component.scss']
})
export class StudentHomeComponent implements OnInit {

  courses: Array<CourseBasic>;
  schools: Array<School>;

  constructor(private studentService: StudentService, private router: Router, private matSnackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.studentService.getStudentsSchools().subscribe(
      response => {
        this.schools = response;
      },
      error => {
        this.matSnackBar.open('Error getting schools');
      }
    );
    this.studentService.getStudentCourses().subscribe(
      response => {
        this.courses = response;
      },
      error => {
        this.matSnackBar.open('Error getting courses');
      }
    );
  }

  goCourse(id) {
    this.router.navigateByUrl('course/' + id);
  }

  goSchool(id) {
    this.router.navigateByUrl('school/' + id);
  }
}
