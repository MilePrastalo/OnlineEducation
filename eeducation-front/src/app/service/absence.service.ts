import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {AddAbsence} from '../model/AddAbsence';
import {Observable} from 'rxjs';
import {Absence} from '../model/Absence';

@Injectable({
  providedIn: 'root'
})
export class AbsenceService {

  path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = this.pathService.path + '/api/absences';
  }

  addAbsence(addAbsence: AddAbsence): Observable<Absence> {
    return this.http.post<Absence>(this.path, addAbsence);
  }

  viewAbsencesOfStudent(): Observable<Array<Absence>> {
    return this.http.get<Array<Absence>>(this.path);
  }

  viewAbsencesInCourse(): Observable<Array<Absence>> {
    return this.http.get<Array<Absence>>(this.path);
  }
}
