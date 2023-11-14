import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AuthRequest } from 'src/app/auth/models/AuthRequest.model';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent {

  authRequest: AuthRequest = {
    mail: "",
    password: "",
    confirm: "",
    username: "",
    birthDate: new Date()
  }

  errorMessage: string = "";

  signUpSub: Subscription | undefined;

  constructor(
    private authService: AuthService
  ) { }

  ngOnDestroy(): void {
    this.signUpSub?.unsubscribe();
  }

  onSubmitSignup(form: NgForm) {
    if (!form.valid) {
      this.errorMessage = "Tous les champs sont requis"
    } else {
      if (this.authRequest.password === this.authRequest.confirm) {
        this.signUpSub = this.authService.signUp(this.authRequest).subscribe({
          next: (data) => {
            this.authService.authenticate(data);
          },
          error: (err) => {
            if (err.status === 422) {
              this.errorMessage = "Vous avez déjà un compte.";
            }
          }
        });
      } else {
        this.errorMessage = "Les mots de passe doivent être identiques"
      }
    }
  }
}
