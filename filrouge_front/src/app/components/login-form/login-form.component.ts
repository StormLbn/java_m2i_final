import { Component } from '@angular/core';
import { AuthRequest } from 'src/app/models/AuthRequest.model';
import { AuthService } from 'src/app/services/auth.service';
import { Subscription } from 'rxjs';


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

  errorMessage = "";

  logInSub: Subscription | undefined;

  constructor(
    private authService: AuthService
  ) {}

  ngOnDestroy(): void {
    this.logInSub?.unsubscribe();
  }

  onSubmitLogin(event: Event) {
    this.authService.logIn(this.authRequest).subscribe({
      next: response => {
        this.authService.authenticate(response);
      },
      error: (err) => {
        if (err.status === 401) {
          this.errorMessage = "Identifiant ou mot de passe incorrect."
        }
      }
    });
  }
}
