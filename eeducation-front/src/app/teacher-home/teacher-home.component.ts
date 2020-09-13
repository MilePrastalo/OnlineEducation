import {Component, OnInit} from '@angular/core';
import {TeacherService} from '../service/teacher.service';
import {School} from '../model/School';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CourseService} from '../service/course.service';
import {CourseBasic} from '../model/CourseBasic';
import {Router} from '@angular/router';

@Component({
  selector: 'app-teacher-home',
  templateUrl: './teacher-home.component.html',
  styleUrls: ['./teacher-home.component.scss']
})
export class TeacherHomeComponent implements OnInit {
  schools: Array<School>;
  courses: Array<CourseBasic>;

  constructor(private snackBar: MatSnackBar, private teacherService: TeacherService, private courseService: CourseService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.teacherService.getTeacherSchools().subscribe(
      response => {
        this.schools = response;
      }, error => {
        this.snackBar.open('Error getting schools');
      }
    );

    this.courseService.getTeacherCourses().subscribe(
      response => {
        this.courses = response;
      }
    );
  }

  createCourse() {
    this.router.navigateByUrl('create-course');
  }

  goCourse(id: number) {
    this.router.navigateByUrl('course/' + id);
  }

  goSchool(id: number) {
    this.router.navigateByUrl('school/' + id);
  }

}
