import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserBasic} from '../model/UserBasic';
import {School} from '../model/School';
import {SchoolDetails} from '../model/SchoolDetails';

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

  inviteTeacher(teacherEmail: string) {
    return this.http.post(this.path + '/invite_teacher/' + teacherEmail, null);
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

  inviteStudent(studentEmail: string) {
    return this.http.post(this.path + '/invite_teacher/' + studentEmail, null);
  }

  acceptSchoolInvitation(schoolId: number) {
    return this.http.post(this.path + '/invitation/' + schoolId, null);
  }

  rejectSchoolInvitation(schoolId: number) {
    return this.http.delete(this.path + '/invitation/' + schoolId);
  }

  getSchoolInvitations() {
    return this.http.get<Array<School>>(this.path + '/schoolInvitations');
  }

  getSchool(schoolId: number): Observable<SchoolDetails> {
    return this.http.get<SchoolDetails>(this.path + '/' + schoolId);
  }
  getSchoolSelf(): Observable<SchoolDetails> {
    return this.http.get<SchoolDetails>(this.path);
  }
}
