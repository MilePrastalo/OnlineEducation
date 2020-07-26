import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
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
      this.router.navigateByUrl('home');
      if (info.exp * 1000 < date.getTime()) {
        localStorage.setItem('token', '')
        return true;
      }
      return false;
    }
    return true;
  }

}
