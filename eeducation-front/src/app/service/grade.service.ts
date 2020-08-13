import {Injectable} from '@angular/core';
import {AddGrade} from '../model/AddGrade';
import {Observable} from 'rxjs';
import {Grade} from '../model/Grade';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  private path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/grades';
  }

  teacherAddsGrade(addGrade: AddGrade): Observable<Grade> {
    return this.http.post<Grade>(this.path, addGrade);
  }

  studentViewGrade(): Observable<Array<Grade>> {
    return this.http.get<Array<Grade>>(this.path);
  }

  viewCourseGrades(courseId: number): Observable<Array<Grade>> {
    return this.http.get<Array<Grade>>(this.path + '/' + courseId);
  }
}
