import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PathService} from './path.service';
import {Observable} from 'rxjs';
import {AuthenticationResponse} from '../model/AuthenticationResponse';
import {AuthenticationRequest} from '../model/AuthenticationRequest';
import {RegisterRequest} from '../model/RegisterRequest';
import {RegisterResponse} from '../model/RegisterResponse';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  path: string;

  constructor(private http: HttpClient, private pathService: PathService) {
    this.path = pathService.path;
  }

  login(authenticationRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(this.path + 'auth/login', authenticationRequest);
  }

  register(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(this.path + 'auth/register', registerRequest);
  }
}
