import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { EvaluationService } from './evaluations/services/evaluation.service';
import { Evaluation } from './evaluations/components/models/Evaluation.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(
    private authService: AuthService
  ) {}

  onClickLogout() {
    this.authService.logOut();
  }
}
