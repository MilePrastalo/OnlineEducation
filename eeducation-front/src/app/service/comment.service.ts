import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AddComment} from '../model/AddComment';
import {Comment} from '../model/Comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/comments';
  }

  getComments(lessonId: number): Observable<Array<Comment>> {
    return this.http.get<Array<Comment>>(this.path + '/' + lessonId);
  }

  addComment(addComment: AddComment): Observable<Comment> {
    return this.http.post<Comment>(this.path, addComment);
  }
}
