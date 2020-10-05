import {Component, OnInit} from '@angular/core';
import {TestResultView} from '../model/TestResultView';
import {Question} from '../model/Question';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TestService} from '../service/test.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AddGrade} from '../model/AddGrade';
import {GradeService} from '../service/grade.service';

@Component({
  selector: 'app-user-test-result',
  templateUrl: './user-test-result.component.html',
  styleUrls: ['./user-test-result.component.scss']
})
export class UserTestResultComponent implements OnInit {

  testResult: TestResultView;
  gradeForm: FormGroup;
  displayedColumns = ['question', 'correct', 'user'];

  constructor(private gradeService:GradeService,private formBuilder: FormBuilder, private testService: TestService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  get grade() {
    return this.gradeForm.controls.grade.value as string;
  }

  ngOnInit(): void {
    this.gradeForm = this.formBuilder.group({
      grade: ['', [
        Validators.required
      ]]
    });
    const testResultId = Number(this.route.snapshot.paramMap.get('id'));
    this.testService.viewStudentTestAnswers(testResultId).subscribe(
      response => {
        this.testResult = response;
      },
      error => {
        this.snackBar.open('Error getting test results');
      }
    );
  }

  getQuestionAnswerText(question: Question): string {
    let answerText = '';
    console.log(this.testResult.userQuestionResults);
    console.log('Qid', question.id);
    this.testResult.userQuestionResults.forEach(userQuestionResult => {
        if (userQuestionResult.questionId === question.id) {
          if (userQuestionResult.answers.length > 0) {
            for (const answer of userQuestionResult.answers) {
              answerText += answer.answerText + ' , ';
            }
          }
        }
      }
    );
    return answerText;
  }

  getquestionCorrectAnswer(question: Question): string {
    let answer = '';
    question.answer.forEach(value => {
      if (value.correct) {
        answer += value.answerText + ' , ';
      }
    });
    return answer;
  }

  addGrade() {
    const addGrade = new AddGrade(this.testResult.student.id, this.testResult.test.courseId, this.grade);
    this.gradeService.teacherAddsGrade(addGrade).subscribe(
      response => {
        this.router.navigateByUrl('testResult/' + this.testResult.test.id);
      }
    );
  }

  back() {

  }

}
