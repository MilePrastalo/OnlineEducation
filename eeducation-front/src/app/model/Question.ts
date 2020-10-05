import {Answer} from './Answer';

export class Question {
  constructor(public id: number,
              public name: string,
              public questionType: string,
              public answer: Array<Answer>,
              public questionPoints: number) {
  }
}
