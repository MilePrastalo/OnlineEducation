import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AuthenticationRequest} from '../model/AuthenticationRequest';
import {JwtHelperService} from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  hide = true;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService,
              private snackBar: MatSnackBar, private router: Router) {
  }

  get email() {
    return this.loginForm.controls.email.value as string;
  }

  get password() {
    return this.loginForm.controls.password.value as string;
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [
        Validators.required
      ]],
      password: ['', [
        Validators.required,
      ]]
    });
  }

  onLogInSubmit() {
    const loginData = new AuthenticationRequest(this.email, this.password);
    this.authenticationService.login(loginData).subscribe(
      (response => {
        if (response != null) {
          localStorage.setItem('token', response.token);
          this.snackBar.open('Logged In successfully.');
          const jwt: JwtHelperService = new JwtHelperService();
          const info = jwt.decodeToken(response.token);
          console.log(info);
          const role = info.role[0].authority;
          localStorage.setItem('role', info.role[0].authority);
          localStorage.setItem('id', String(response.user.id));
          this.snackBar.open('Logged In successfully.');

          if (role === 'SCHOOL') {
            this.router.navigateByUrl('/school-home');
          } else if (role === 'STUDENT') {
            this.router.navigateByUrl('/student-home');
          } else if (role === 'TEACHER') {
            this.router.navigateByUrl('/teacher-home');
          }
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      }));
  }


}
