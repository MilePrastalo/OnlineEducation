import {Component, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {CourseService} from '../service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  course: Course;
  courseId: number;
  role: string;
  id: string;
  displayedColumns = ['name', 'email', 'accept', 'reject'];

  constructor(private courseService: CourseService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    this.id = localStorage.getItem('id');
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.courseService.getCourse(this.courseId).subscribe(
      response => {
        this.course = response;
      },
      error => {
        alert(error);
      }
    );
  }

  edit() {
    this.router.navigateByUrl('edit-course/' + this.courseId);
  }

  archive() {
    this.courseService.archiveCourse(this.courseId).subscribe(
      response => {
        this.snackBar.open('Course has been archived');
      },
      error => {
        this.snackBar.open('Error trying to archive course');

      }
    );
  }

  studentPartOfCourse(): boolean {
    for (const student of this.course.students) {
      if (student.id === Number(this.id)) {
        return true;
      }
    }
    return false;
  }

  teacherPartOfCourse(): boolean {
    if (this.role === 'STUDENT') {
      return false;
    } else if (this.role === 'TEACHER') {
      for (const t of this.course.teachers) {
        if (t.id === Number(this.id)) {
          return true;
        }
      }
      return false;
    } else if (this.role === 'SCHOOL') {
      return this.course.school.id === Number(this.id);
    }
    return false;

  }

  studentNotRequested() {
    if (this.role === 'STUDENT') {
      for (const stud of this.course.students) {
        if (stud.id === Number(this.id)) {
          return false;
        }
      }
      for (const stud of this.course.studentsWaiting) {
        if (stud.id === Number(this.id)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  joinCourse() {
    this.courseService.joinCourse(this.course.id).subscribe(
      response => {
        this.course = response;
      },
      error => {
        this.snackBar.open('Error joining course');
      }
    );
  }

  acceptStudentRequest(id: number) {
    this.courseService.acceptStudentRequest(this.courseId, id).subscribe(
      response => {
        this.course = response;
      }, error => {
        this.snackBar.open('Error accepting student');
      }
    );
  }

  rejectStudentRequest(id: number) {
    this.courseService.rejectStudentRequest(this.courseId, id).subscribe(
      response => {
        this.course = response;
      }, error => {
        this.snackBar.open('Error rejecting student');
      }
    );
  }
}
