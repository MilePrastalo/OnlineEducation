import {Component, OnInit} from '@angular/core';
import {UserBasic} from '../model/UserBasic';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SchoolService} from '../service/school.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {CourseBasic} from '../model/CourseBasic';
import {SchoolDetails} from '../model/SchoolDetails';

@Component({
  selector: 'app-school-home',
  templateUrl: './school-home.component.html',
  styleUrls: ['./school-home.component.scss']
})
export class SchoolHomeComponent implements OnInit {

  teacherRequests: Array<UserBasic>;
  studentRequests: Array<UserBasic>;
  inviteStudentForm: FormGroup;
  inviteTeacherForm: FormGroup;
  courses: Array<CourseBasic>;
  teachers: Array<UserBasic>;
  school: SchoolDetails;
  displayedColumnsTeachers = ['email', 'name'];
  displayedColumnsTeachersRequests= ['email', 'name', 'accept', 'reject'];
  constructor(private formBuilder: FormBuilder, private schoolService: SchoolService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  get studentInviteEmail() {
    return this.inviteStudentForm.controls.studentInviteEmail.value as string;
  }

  get teacherInviteEmail() {
    return this.inviteTeacherForm.controls.teacherInviteEmail.value as string;
  }

  ngOnInit(): void {
    this.inviteStudentForm = this.formBuilder.group({
      studentInviteEmail: ['', [
        Validators.required
      ]]
    });
    this.inviteTeacherForm = this.formBuilder.group({
      teacherInviteEmail: ['', [
        Validators.required
      ]]
    });
    this.schoolService.viewTeacherRequests().subscribe(
      response => {
        this.teacherRequests = response;
        console.log(response);
      },
      error => {
        this.snackBar.open('Error getting teacher requests');
      }
    );
    this.schoolService.viewStudentRequests().subscribe(
      response => {
        this.studentRequests = response;
      },
      error => {
        this.snackBar.open('Error getting student requests');
      }
    );
    this.schoolService.getSchoolSelf().subscribe(
      response => {
        console.log(response);
        this.school = response;
      },
      error => {
        this.snackBar.open('Error getting school');
      }
    );
  }

  acceptTeacherRequest(teacherId: number) {
    this.schoolService.acceptTeacherRequest(teacherId).subscribe(
      response => {
        this.schoolService.viewTeacherRequests().subscribe(
          next => {
            this.teacherRequests = next;
          }
        );
      },
      error => {
        this.snackBar.open('Error accepting request');
      }
    );
  }

  rejectTeacherRequest(teacherId: number) {
    this.schoolService.rejectTeacherRequest(teacherId).subscribe(
      response => {
        this.schoolService.viewTeacherRequests().subscribe(
          next => {
            this.teacherRequests = next;
          }
        );
      },
      error => {
        this.snackBar.open('Error accepting request');
      }
    );
  }

  acceptStudentRequest(studentId: number) {
    this.schoolService.acceptStudentRequest(studentId).subscribe(
      response => {
        this.schoolService.viewStudentRequests().subscribe(
          next => {
            this.studentRequests = next;
          }
        );
      },
      error => {
        this.snackBar.open('Error accepting request');
      }
    );
  }

  rejectStudentRequest(studentId: number) {
    this.schoolService.rejectStudentRequest(studentId).subscribe(
      response => {
        this.schoolService.viewStudentRequests().subscribe(
          next => {
            this.studentRequests = next;
          }
        );
      },
      error => {
        this.snackBar.open('Error accepting request');
      }
    );
  }

  onInviteStudent() {
    const email = btoa(this.studentInviteEmail);
    this.schoolService.inviteStudent(email).subscribe(
      response => {
        this.snackBar.open('Invitation sent');
      },
      error => {
        this.snackBar.open('Error sending invitation');
      }
    );
  }

  onInviteTeacher() {
    const email = btoa(this.teacherInviteEmail);
    this.schoolService.inviteTeacher(email).subscribe(
      response => {
        this.snackBar.open('Invitation sent');
      },
      error => {
        this.snackBar.open('Error sending invitation');
      }
    );
  }

  goCourse(id: number) {
    this.router.navigateByUrl('course/' + id);
  }
}
