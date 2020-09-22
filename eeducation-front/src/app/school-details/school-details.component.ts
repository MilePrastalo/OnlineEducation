import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SchoolService} from '../service/school.service';
import {FormBuilder} from '@angular/forms';
import {SchoolDetails} from '../model/SchoolDetails';

@Component({
  selector: 'app-school-details',
  templateUrl: './school-details.component.html',
  styleUrls: ['./school-details.component.scss']
})
export class SchoolDetailsComponent implements OnInit {

  schoolId: number;

  school: SchoolDetails;
  role: string;

  constructor(private formBuilder: FormBuilder, private schoolService: SchoolService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }


  ngOnInit(): void {
    this.role = localStorage.getItem('role');

    this.schoolId = Number(this.route.snapshot.paramMap.get('id'));
    this.schoolService.getSchool(this.schoolId).subscribe(
      response => {
        this.school = response;
      }
    );
  }

  askToJoinSchoolTeacher() {
    this.schoolService.askToJoinSchoolTeacher(this.schoolId).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }

  askToJoinSchoolStudent() {
    this.schoolService.askToJoinSchoolStudent(this.schoolId).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }

  studentAskedToJoin(): boolean {
    const id = Number(localStorage.getItem('id'));
    for (const student of this.school.students) {
      if (student.id === id) {
        return true;
      }
    }
    for (const student of this.school.studentRequests) {
      if (student.id === id) {
        return true;
      }
    }
    return false;
  }

  teacherAskedToJoin(): boolean {
    const id = Number(localStorage.getItem('id'));
    for (const teacher of this.school.teachers) {
      if (teacher.id === id) {
        return true;
      }
    }
    for (const teacher of this.school.teacherRequests) {
      if (teacher.id === id) {
        return true;
      }
    }
    return false;
  }

  teacherCanJoin(): boolean {
    return this.role === 'TEACHER' && (!this.teacherAskedToJoin());
  }

  studentCanJoin(): boolean {
    return this.role === 'STUDENT' && (!this.studentAskedToJoin());
  }

}
