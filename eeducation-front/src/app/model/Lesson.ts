import {Absence} from './Absence';

export class Lesson {
  constructor(public id: number,
              public absences: Array<Absence>,
              public comments: Array<Comment>,
              public lessonContent: string,
              public name: string,
              public date: Date) {
  }
}
