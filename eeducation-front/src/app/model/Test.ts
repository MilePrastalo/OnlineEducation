import {Question} from './Question';

export class Test {
  constructor(public id: number,
              public name: string,
              public date: string,
              public duration: number,
              public testType: string,
              public questions: Array<Question>,
              public courseId: number) {
  }
}
