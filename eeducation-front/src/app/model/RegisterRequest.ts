export class RegisterRequest {
  constructor(public name: string,
              public email: string,
              public password: string,
              public confirmedPassword: string,
              public country: string,
              public userType: string) {
  }
}
