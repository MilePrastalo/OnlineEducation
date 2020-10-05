import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Question} from '../model/Question';
import {Answer} from '../model/Answer';

@Component({
  selector: 'app-answer-dialog',
  templateUrl: './answer-dialog.component.html',
  styleUrls: ['./answer-dialog.component.scss']
})
export class AnswerDialogComponent implements OnInit {

  selectedValue = 'PERFECT_MATCH';

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
  }

  canBeCorrect(question: Question): boolean {
    if (question.questionType === 'CHECKBOXES') {
      return true;
    } else if (question.questionType === 'MULTIPLE_CHOICE') {
      for (const answer of question.answer) {
        if (answer.correct) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  changeSelectedValue($event) {
    this.selectedValue = $event.value;
  }

  getAnswer() {
    let answer: Answer;
    if (this.data.question.questionType !== 'SHORT_ANSWER') {
      answer = new Answer(null, this.data.text, this.data.checked, null);
    } else {
      answer = new Answer(null, this.data.text, this.data.checked, this.selectedValue);
    }
    return answer;
  }
}
