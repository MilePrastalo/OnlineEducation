export class CreateCourse {
  constructor(public name: string, public begins: string, public ends: string, public school: number, public teachers: Array<string>) {
  }
}
