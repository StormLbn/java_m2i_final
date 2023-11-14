import { Component } from '@angular/core';
import { AuthRequest } from 'src/app/auth/models/AuthRequest.model';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';


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
  ) { }

  ngOnDestroy(): void {
    this.logInSub?.unsubscribe();
  }

  onSubmitLogin(form: NgForm) {
    if (!form.valid) {
      this.errorMessage = "Tous les champs sont requis";
    } else {
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
}
