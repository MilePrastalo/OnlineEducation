import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {Question} from '../model/Question';
import {QuestionDialogComponent} from '../question-dialog/question-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AnswerDialogComponent} from '../answer-dialog/answer-dialog.component';

@Component({
  selector: 'app-create-test',
  templateUrl: './create-test.component.html',
  styleUrls: ['./create-test.component.scss']
})
export class CreateTestComponent implements OnInit {

  testForm: FormGroup;

  questions = new Array<Question>();

  constructor(private formBuilder: FormBuilder,
              private snackBar: MatSnackBar,
              private router: Router,
              private route: ActivatedRoute,
              public dialog: MatDialog) {
  }

  get name() {
    return this.testForm.controls.name.value as string;
  }

  get date() {
    return this.testForm.controls.date.value as string;
  }

  get duration() {
    return this.testForm.controls.duration.value as string;
  }

  ngOnInit(): void {
    this.testForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      date: ['', [
        Validators.required,
      ]],
      duration: ['', [
        Validators.required,
      ]]
    });
  }

  addQuestion() {
    const dialogRef = this.dialog.open(QuestionDialogComponent, {
      width: '250px',
      data: {text: '', type: ''}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result);
        this.questions.push(result);
      }
    });
  }


  addAnswer(q: Question) {
    console.log(q);
    const dialogRef = this.dialog.open(AnswerDialogComponent, {
      width: '250px',
      data: {question: q, text: '', checked: false}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result);
        q.answer.push(result);
      }
    });
  }

}
