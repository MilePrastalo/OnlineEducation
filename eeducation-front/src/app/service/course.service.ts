import {Injectable} from '@angular/core';
import {CreateCourse} from '../model/CreateCourse';
import {Observable} from 'rxjs';
import {Course} from '../model/Course';
import {HttpClient} from '@angular/common/http';
import {PathService} from './path.service';
import {CourseBasic} from '../model/CourseBasic';
import {EditCourse} from '../model/EditCourse';
import {Student} from '../model/Student';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private path = '';

  constructor(private http: HttpClient, private pathService: PathService) {
    this.path = pathService.path + '/api/courses';
  }

  addCourse(createCourse: CreateCourse): Observable<Course> {
    return this.http.post<Course>(this.path, createCourse);
  }

  getTeacherCourses(): Observable<Array<CourseBasic>> {
    return this.http.get<Array<CourseBasic>>(this.path + '/teacher' );
  }

  getCourse(courseId: number): Observable<Course> {
    return this.http.get<Course>(this.path + '/' + courseId);
  }

  editCourse(editCourse: EditCourse): Observable<Course> {
    return this.http.put<Course>(this.path, editCourse);
  }

  archiveCourse(courseId: number): Observable<Course> {
    return this.http.delete<Course>(this.path + '/' + courseId);
  }

  joinCourse(courseId: number): Observable<Course> {
    return this.http.get<Course>(this.path + '/join/' + courseId);
  }

  viewStudentsOfClass(courseId: number): Observable<Array<Student>> {
    return this.http.get<Array<Student>>(this.path + '/students/' + courseId);
  }

}
