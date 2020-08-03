import {Grade} from './Grade';
import {Test} from './Test';
import {Absence} from './Absence';

export class Course {
  public id: number;
  public lections: Array<string>;
  public students: Array<string>;
  public teachers: Array<string>;
  public school: string;
  public grades: Array<Grade>;
  public tests: Array<Test>;
  public absences: Array<Absence>;
  public name: string;
  public begins: Date;
  public ends: Date;
}
