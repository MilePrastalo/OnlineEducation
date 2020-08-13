import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {School} from '../model/School';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  private path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/teacher';
  }

  getTeacherSchools(): Observable<Array<School>> {
    return this.http.get<Array<School>>(this.path + '/schools');
  }
}
