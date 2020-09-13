import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {CourseBasic} from '../model/CourseBasic';
import {Observable} from 'rxjs';
import {School} from '../model/School';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/search';
  }

  searchCourses(name: string): Observable<Array<CourseBasic>> {
    return this.http.get<Array<CourseBasic>>(this.path + '/course/' + name);
  }

  searchSchools(name: string): Observable<Array<School>> {
    return this.http.get<Array<School>>(this.path + '/school/' + name);
  }
}
