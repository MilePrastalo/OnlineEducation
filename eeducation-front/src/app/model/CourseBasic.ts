export class CourseBasic {
  constructor(public id: number,
              public name: string,
              public teachers: Array<string>,
              public school: string,
              public begins: Date,
              public ends: Date) {
  }
}
