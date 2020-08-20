export class EditLesson {
  constructor(public id: number,
              public courseId: number,
              public lessonContent: string,
              public name: string,
              public date: string) {
  }
}
