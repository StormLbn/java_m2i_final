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

  evaluations: Evaluation[] = [];

  constructor(
    private authService: AuthService,
    private evalService: EvaluationService
  ) {
    this.evalService.getEvaluationsForMedia("53be62a6-4d7d-407f-af57-6b93be4abaf7", 0).subscribe(data => this.evaluations = data);
  }

  onClickLogout() {
    this.authService.logOut();
  }
}
