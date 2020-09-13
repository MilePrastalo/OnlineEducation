import {Grade} from './Grade';
import {Test} from './Test';
import {Absence} from './Absence';
import {Student} from './Student';
import {Lesson} from './Lesson';
import {School} from './School';
import {UserBasic} from './UserBasic';

export class Course {
  public id: number;
  public lessons: Array<Lesson>;
  public students: Array<Student>;
  public teachers: Array<UserBasic>;
  public school: School;
  public grades: Array<Grade>;
  public tests: Array<Test>;
  public absences: Array<Absence>;
  public name: string;
  public begins: Date;
  public ends: Date;
  public description: string;
  public studentsWaiting: Array<UserBasic>;

}
