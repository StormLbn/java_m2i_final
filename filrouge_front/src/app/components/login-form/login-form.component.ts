import { Component } from '@angular/core';
import { AuthRequest } from 'src/app/models/AuthRequest.model';
import { AuthService } from 'src/app/services/auth.service';

let authRequest: AuthRequest = {
  mail: "storm@mail.fr",
  password: "aze123"
}

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  
  constructor(
    private authService: AuthService
  ) {}

  onSubmitLogin(event: Event) {
    event.preventDefault();

    this.authService.logIn(authRequest);
  }
}
