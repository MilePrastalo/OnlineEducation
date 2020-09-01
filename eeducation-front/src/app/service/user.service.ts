import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UserBasic} from '../model/UserBasic';
import {HttpClient} from '@angular/common/http';
import {PathService} from './path.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private path = '';

  constructor(private http: HttpClient, private pathService: PathService) {
    this.path = pathService.path + '/api/user';
  }

  getUser(): Observable<UserBasic> {
    return this.http.get<UserBasic>(this.path);
  }
}
