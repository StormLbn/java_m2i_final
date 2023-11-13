import { Component } from '@angular/core';
import { Router } from '@angular/router';
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

  authRequest: AuthRequest = {
    mail: "",
    password: ""
  };

  logInSub: PushSubscription | undefined;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnDestroy(): void {
    this.logInSub?.unsubscribe();
  }

  onSubmitLogin(event: Event) {
    event.preventDefault();

    this.authService.logIn(this.authRequest).subscribe({
      next: response => this.authService.authenticate(response),
      error: (err) => console.log(err),
      complete: () => this.router.navigate([''])
    });
  }
}
