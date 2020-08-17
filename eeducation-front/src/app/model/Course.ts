import {Grade} from './Grade';
import {Test} from './Test';
import {Absence} from './Absence';
import {Student} from './Student';
import {Lesson} from './Lesson';

export class Course {
  public id: number;
  public lessons: Array<Lesson>;
  public students: Array<Student>;
  public teachers: Array<string>;
  public school: string;
  public grades: Array<Grade>;
  public tests: Array<Test>;
  public absences: Array<Absence>;
  public name: string;
  public begins: Date;
  public ends: Date;
  public description: string;
}
