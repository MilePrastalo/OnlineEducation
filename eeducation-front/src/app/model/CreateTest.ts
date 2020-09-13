import {Question} from './Question';

export class CreateTest {
  constructor(public name: string,
              public date: string,
              public duration: number,
              public testType: string,
              public questions: Array<Question>,
              public courseId: number) {
  }
}
