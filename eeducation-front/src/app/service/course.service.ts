import {Injectable} from '@angular/core';
import {CreateCourse} from '../model/CreateCourse';
import {Observable} from 'rxjs';
import {Course} from '../model/Course';
import {HttpClient} from '@angular/common/http';
import {PathService} from './path.service';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private path = '';

  constructor(private http: HttpClient, private pathService: PathService) {
    this.path = pathService.path;
  }

  createCourse(createCourse: CreateCourse): Observable<Course> {
    return this.http.post<Course>(this.path + 'api/courses', createCourse);
  }
}
