import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserBasic} from '../model/UserBasic';

@Injectable({
  providedIn: 'root'
})
export class SchoolService {

  private path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/school';
  }

  askToJoinSchoolTeacher(schoolId: number) {
    return this.http.post(this.path + '/teacher_request/' + schoolId, null);
  }

  viewTeacherRequests(): Observable<Array<UserBasic>> {
    return this.http.get<Array<UserBasic>>(this.path + '/teacher_request');
  }

  acceptTeacherRequest(teacherId: number) {
    return this.http.put(this.path + '/teacher_request/' + teacherId, null);
  }

  rejectTeacherRequest(teacherId: number) {
    return this.http.delete(this.path + '/teacher_request/' + teacherId);
  }

  inviteTeacher(teacherId: number) {
    return this.http.post(this.path + '/invite_teacher/' + teacherId, null);
  }

  askToJoinSchoolStudent(schoolId: number) {
    return this.http.post(this.path + '/student_request/' + schoolId, null);
  }

  viewStudentRequests(): Observable<Array<UserBasic>> {
    return this.http.get<Array<UserBasic>>(this.path + '/student_request');
  }

  acceptStudentRequest(studentId: number) {
    return this.http.put(this.path + '/student_request/' + studentId, null);
  }

  rejectStudentRequest(studentId: number) {
    return this.http.delete(this.path + '/student_request/' + studentId);
  }

  inviteStudent(studentId: number) {
    return this.http.post(this.path + '/invite_teacher/' + studentId, null);
  }

  acceptSchoolInvitation(schoolId: number) {
    return this.http.post(this.path + '/invitation/' + schoolId, null);
  }

  rejectSchoolInvitation(schoolId: number) {
    return this.http.delete(this.path + '/invitation/' + schoolId);
  }
}
