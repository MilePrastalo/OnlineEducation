import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {School} from '../model/School';
import {HttpClient} from '@angular/common/http';
import {PathService} from './path.service';
import {CourseBasic} from '../model/CourseBasic';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private path = '';

  constructor(private http: HttpClient, private pathService: PathService) {
    this.path = pathService.path + '/api/students';
  }

  getStudentsSchools(): Observable<Array<School>> {
    return this.http.get<Array<School>>(this.path + '/schools');
  }
  getStudentCourses(): Observable<Array<CourseBasic>> {
    return this.http.get<Array<CourseBasic>>(this.path + '/courses');
  }
}
