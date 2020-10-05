import {Component, OnInit} from '@angular/core';
import {Test} from '../model/Test';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {TestService} from '../service/test.service';
import * as moment from 'moment';
import {TestResult} from '../model/TestResult';
import {Answer} from '../model/Answer';
import {UserQuestionResult} from '../model/UserQuestionResult';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  test: Test;
  testId: number;
  timer = '30:30';
  timeRemaining: number;
  testResults: TestResult;
  score: string;

  constructor(private testService: TestService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.testId = Number(this.route.snapshot.paramMap.get('id'));
    this.testService.getTest(this.testId).subscribe(
      response => {
        this.test = response;
        this.sortQuestions();
        const dateTimeSplitted = this.test.date.split(' ');
        const dateDate = dateTimeSplitted[0];
        const time = dateTimeSplitted[1];

        const dateDateSplitted = dateDate.split('/');
        const timeSplitted = time.split(':');
        const startDate = new Date(Number(dateDateSplitted[2]), Number(dateDateSplitted[1]),
          Number(dateDateSplitted[0]), Number(timeSplitted[0]), Number(timeSplitted[1]));
        const endDate = moment(startDate).add(this.test.duration, 'm').toDate();
        this.timeRemaining = Math.abs(endDate.getTime() - (new Date()).getTime()) / 1000;
        const x = setInterval(() => {
          this.timeRemaining = this.timeRemaining -= 1;
          const minutes = this.timeRemaining / 60;
          const seconds = this.timeRemaining % 60;
          this.timer = Math.floor(minutes) + ':' + Math.floor(seconds);
        }, 1000);
      },
      error => {
        this.snackBar.open('Error getting test');
      }
    );
    this.testResults = new TestResult(this.testId);
  }

  sortQuestions() {
    for (let i = 0; i < this.test.questions.length - 1; i++) {
      for (let j = i + 1; j < this.test.questions.length; j++) {
        if (this.test.questions[j].id < this.test.questions[i].id) {
          const temp = this.test.questions[i];
          this.test.questions[i] = this.test.questions[j];
          this.test.questions[j] = temp;
        }
      }
    }
  }

  questionAnsweredRadio(questionId, answerText, answerId, $event) {
    let exist = false;
    console.log(answerId);
    for (const question of this.testResults.userQuestionResults) {
      if (question.questionId === questionId) {
        exist = true;
        question.answers = [];
        const answer = new Answer(answerId, answerText, false, null);
        question.answers.push(answer);
        return;
      }
    }
    if (!exist) {
      const userQuestionResult = new UserQuestionResult();
      userQuestionResult.questionId = questionId;
      userQuestionResult.answers = [new Answer(answerId, answerText, false, null)];
      this.testResults.userQuestionResults.push(userQuestionResult);
    }
    console.log(this.testResults);
  }

  questionAnsweredChecked(questionId, answerText, answerId, $event) {
    let exist = false;
    for (const question of this.testResults.userQuestionResults) {
      if (question.questionId === questionId) {
        exist = true;
        if ($event.checked) {
          question.answers.push(new Answer(answerId, answerText, false, null));
        } else {
          for (let i = 0; i < question.answers.length; i++) {
            if (question.answers[i].id === answerId) {
              question.answers.splice(i, 1);
            }
          }
        }
      }
    }
    if (!exist) {
      const userQuestionResult = new UserQuestionResult();
      userQuestionResult.questionId = questionId;
      userQuestionResult.answers = [new Answer(answerId, answerText, false, null)];
      this.testResults.userQuestionResults.push(userQuestionResult);
    }
  }

  questionTextAnswer(questionId, $event) {
    const input = (document.getElementById(questionId) as HTMLInputElement).value;
    let exist = false;
    for (const question of this.testResults.userQuestionResults) {
      if (question.questionId === questionId) {
        exist = true;
        const answer = new Answer(null, input, false, null);
        question.answers = [answer];
      }
    }
    if (!exist) {
      const userQuestionResult = new UserQuestionResult();
      userQuestionResult.questionId = questionId;
      userQuestionResult.answers = [new Answer(null, input, false, null)];
      this.testResults.userQuestionResults.push(userQuestionResult);
    }
  }

  finish() {
    this.testResults.date = new Date();
    console.log(this.testResults);
    this.testService.studentSubmitsTest(this.testResults).subscribe(
      response => {
        this.testResults = response;
        this.score = this.testResults.score;
      }
    );
  }
  back(){
    this.router.navigateByUrl('course/' + this.test.courseId);
  }

}
