import {Injectable} from '@angular/core';
import {Course} from '../model/Course';

@Injectable({
  providedIn: 'root'
})
export class VisibilityService {

  constructor() {
  }

  teacherPartOfCourse(course: Course): boolean {
    const role = localStorage.getItem('role');
    const id = localStorage.getItem('id');
    if (role === 'STUDENT') {
      return false;
    } else if (role === 'TEACHER') {
      for (const t of course.teachers) {
        if (t.id === Number(id)) {
          return true;
        }
      }
      return false;
    } else if (role === 'SCHOOL') {
      return course.school.id === Number(id);
    }
    return false;
  }
}
