import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthRequest } from 'src/app/models/AuthRequest.model';
import { AuthService } from 'src/app/services/auth.service';

let newUser: AuthRequest = {
  mail: "front-2@test.fr",
  password: "aze123",
  username: "Test inscription Front",
  birthDate: new Date()
}

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent {

  signUpSub: Subscription | undefined;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnDestroy(): void {
    this.signUpSub?.unsubscribe();
  }

  onSubmitSignup(event: Event) {
    event.preventDefault();

    this.signUpSub = this.authService.signUp(newUser).subscribe({
      next: (data) => this.authService.authenticate(data),
      error: (err) => {console.log("Erreur : "); console.log(err)},
      complete: () => {
        this.router.navigate(['']);
        console.log("Utilisateur enregistré");
        
      }
    });
  }
}
