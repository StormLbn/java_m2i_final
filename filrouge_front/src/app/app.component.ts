import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { AuthRequest } from './models/AuthRequest.model';

let authRequest: AuthRequest = {
  mail: "storm@mail.fr",
  password: "aze123"
}

let newUser: AuthRequest = {
  mail: "front-2@test.fr",
  password: "aze123",
  username: "Test inscription Front",
  birthDate: new Date()
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(
    private authService: AuthService
  ) {}

  onClickLogin() {
    this.authService.logIn(authRequest);
  }
  
  onClickSignup() {
    this.authService.signUp(newUser);
  }
  
    onClickLogout() {
      this.authService.logOut();
    }
}
