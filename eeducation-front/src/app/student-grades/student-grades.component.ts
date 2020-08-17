import {Component, OnInit} from '@angular/core';
import {Grade} from '../model/Grade';
import {GradeService} from '../service/grade.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Router} from '@angular/router';

@Component({
  selector: 'app-student-grades',
  templateUrl: './student-grades.component.html',
  styleUrls: ['./student-grades.component.scss']
})
export class StudentGradesComponent implements OnInit {

  grades: Array<Grade>;

  constructor(private gradeService: GradeService, private snackBar: MatSnackBar, private router: Router) {
  }

  ngOnInit(): void {
    this.gradeService.studentViewGrade().subscribe(
      response => {
        this.grades = response;
      },
      error => {
        this.snackBar.open('Error getting grades');
      }
    );
  }

}
