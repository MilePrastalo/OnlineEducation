export class CreateLesson {
  constructor(public courseId: number,
              public lessonContent: string,
              public name: string,
              public date: Date) {
  }
}
