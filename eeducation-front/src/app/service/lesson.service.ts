import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Lesson} from '../model/Lesson';
import {CreateLesson} from '../model/CreateLesson';
import {EditLesson} from '../model/EditLesson';

@Injectable({
  providedIn: 'root'
})
export class LessonService {

  private path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/lessons';
  }

  createLesson(createLesson: CreateLesson): Observable<Lesson> {
    return this.http.post<Lesson>(this.path, createLesson);
  }

  editLesson(editLesson: EditLesson): Observable<Lesson> {
    return this.http.put<Lesson>(this.path, editLesson);
  }

  deleteLesson(lessonId: number) {
    return this.http.delete(this.path + '/' + lessonId);
  }

  getLesson(lessonId: number): Observable<Lesson> {
    return this.http.get<Lesson>(this.path + '/' + lessonId);
  }
}
