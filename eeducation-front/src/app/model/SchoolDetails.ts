import {UserBasic} from './UserBasic';
import {CourseBasic} from './CourseBasic';

export class SchoolDetails {
  constructor(public id: number,
              public email: string,
              public name: string,
              public courses: Array<CourseBasic>,
              public teachers: Array<UserBasic>,
              public students: Array<UserBasic>,
              public teacherRequests: Array<UserBasic>,
              public studentRequests: Array<UserBasic>) {

  }
}
