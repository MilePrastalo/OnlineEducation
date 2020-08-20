import {Absence} from './Absence';
import {Comment} from './Comment';

export class Lesson {
  constructor(public id: number,
              public absences: Array<Absence>,
              public comments: Array<Comment>,
              public lessonContent: string,
              public name: string,
              public date: Date,
              public courseId: number) {
  }
}
