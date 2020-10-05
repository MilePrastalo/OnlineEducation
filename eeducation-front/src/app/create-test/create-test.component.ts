import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ActivatedRoute, Router} from '@angular/router';
import {Question} from '../model/Question';
import {QuestionDialogComponent} from '../question-dialog/question-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {AnswerDialogComponent} from '../answer-dialog/answer-dialog.component';
import {CreateTest} from '../model/CreateTest';
import {TestService} from '../service/test.service';

@Component({
  selector: 'app-create-test',
  templateUrl: './create-test.component.html',
  styleUrls: ['./create-test.component.scss']
})
export class CreateTestComponent implements OnInit {

  testForm: FormGroup;
  courseId: number;
  questions = new Array<Question>();
  testDate = '';

  constructor(private formBuilder: FormBuilder,
              private snackBar: MatSnackBar,
              private router: Router,
              private route: ActivatedRoute,
              public dialog: MatDialog,
              private testService: TestService
  ) {
  }

  get name() {
    return this.testForm.controls.name.value as string;
  }

  get date() {
    return this.testForm.controls.date.value as string;
  }

  get duration() {
    return this.testForm.controls.duration.value as number;
  }

  get testType() {
    return this.testForm.controls.testType.value as string;
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
      ]],
      testType: ['', [
        Validators.required,
      ]]
    });
    this.testForm.controls.testType.setValue('SELF_GRADING');
    this.courseId = Number(this.route.snapshot.paramMap.get('course'));

  }


  addQuestion() {
    const dialogRef = this.dialog.open(QuestionDialogComponent, {
      width: '250px',
      data: {text: '', type: '', testType: this.testType}
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
      data: {question: q, text: '', checked: false, questionType: q.questionType}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log(result);
        q.answer.push(result);
      }
    });
  }

  publishTest() {
    const createTest = new CreateTest(this.name, this.date, this.duration, this.testType, this.questions, this.courseId);
    let displayAlert = false;
    if (this.testType === 'SELF_GRADING') {
      for (const question of this.questions) {
        if (question.questionType === 'PARAGRAPH') {
          displayAlert = true;
          break;
        }
      }
      if (displayAlert) {
        alert('Auto grading is not possible. Please select Manual grading');
      } else {
        this.testService.createTest(createTest).subscribe(
          response => {
            this.router.navigateByUrl('course/' + this.courseId);
          }
        );
      }
    }
  }

  getQuestionTypeString(questionType: string): string {
    if (questionType === 'MULTIPLE_CHOICE') {
      return 'Single answer';
    } else if (questionType === 'CHECKBOXES') {
      return 'Multiple answers';
    } else if (questionType === 'SHORT_ANSWER') {
      return 'short answer';
    } else {
      return 'Long answer';
    }
  }

}
