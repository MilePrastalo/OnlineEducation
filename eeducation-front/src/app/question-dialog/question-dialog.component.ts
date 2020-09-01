import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Question} from '../model/Question';
import {Answer} from '../model/Answer';

@Component({
  selector: 'app-question-dialog',
  templateUrl: './question-dialog.component.html',
  styleUrls: ['./question-dialog.component.scss']
})
export class QuestionDialogComponent implements OnInit {

  question: Question;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
  }

  getQuestion(): Question {
    const question = new Question(null, this.data.text, this.data.type, new Array<Answer>());
    return question;
  }

}
