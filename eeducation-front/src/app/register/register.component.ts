import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AuthenticationRequest} from '../model/AuthenticationRequest';
import {JwtHelperService} from '@auth0/angular-jwt';
import {RegisterRequest} from '../model/RegisterRequest';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  hide = true;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService,
              private snackBar: MatSnackBar, private router: Router) {
  }


  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      email: ['', [
        Validators.required
      ]],
      name: ['', [
        Validators.required
      ]],
      country: ['', [
        Validators.required
      ]],
      userType: ['', [
        Validators.required
      ]],
      confirmedPassword: ['', [
        Validators.required
      ]],
      password: ['', [
        Validators.required,
      ]]
    });
  }

  get email() {
    return this.registerForm.controls.email.value as string;
  }

  get name() {
    return this.registerForm.controls.name.value as string;
  }

  get country() {
    return this.registerForm.controls.country.value as string;
  }

  get userType() {
    return this.registerForm.controls.userType.value as string;
  }

  get password() {
    return this.registerForm.controls.password.value as string;
  }

  get confirmedPassword() {
    return this.registerForm.controls.confirmedPassword.value as string;
  }


  onRegisterSubmit() {
    const register = new RegisterRequest(this.name, this.email, this.password, this.confirmedPassword, this.country, this.userType);
    console.log(register)
    this.authenticationService.register(register).subscribe(
      (response => {
        if (response != null) {
          this.snackBar.open('Registered successfully.');
          console.log(response.response)
          this.router.navigateByUrl('index');
        }
      }),
      (error => {
        this.snackBar.open(error.error.message);
      }));
  }
}
