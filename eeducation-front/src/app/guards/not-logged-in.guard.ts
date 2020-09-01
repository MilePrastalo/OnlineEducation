import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class NotLoggedInGuard implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const token = localStorage.getItem('token');
    if (token) {
      const jwt: JwtHelperService = new JwtHelperService();
      const info = jwt.decodeToken(token);
      const date = new Date();
      const role = localStorage.getItem('role');
      if (role === 'SCHOOL') {
        this.router.navigateByUrl('/school-home');
      } else if (role === 'STUDENT') {
        this.router.navigateByUrl('/student-home');
      } else if (role === 'TEACHER') {
        this.router.navigateByUrl('/teacher-home');
      } else {
        return true;
      }
      if (info.exp * 1000 < date.getTime()) {
        localStorage.setItem('token', '');
        return true;
      }
      return false;
    }
    return true;
  }

}
