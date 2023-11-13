import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';
import { ApiRequestService } from './services/api-request.service';
import { Router } from '@angular/router';
import { AuthRequest } from './models/AuthRequest.model';

const authRequest: AuthRequest = {
  mail: "storm@mail.fr",
  password: "aze123"
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(
    private authService: AuthService,
    private api: ApiRequestService,
    private router: Router
  ) {}

  onClickLogin() {
    this.api.logIn(authRequest).subscribe(response => this.authService.logIn(response));
  }

  onClickLogout() {
    this.authService.logOut();
  }
}
