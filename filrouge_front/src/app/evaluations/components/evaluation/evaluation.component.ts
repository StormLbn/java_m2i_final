import { Component, Input } from '@angular/core';
import { Evaluation } from '../../models/Evaluation.model';
import { AuthService } from 'src/app/auth/services/auth.service';


@Component({
  selector: 'app-evaluation',
  templateUrl: './evaluation.component.html',
  styleUrls: ['./evaluation.component.css']
})
export class EvaluationComponent {

  @Input({
    required: true
  })
  evaluation!: Evaluation;
  
  @Input({
    required: true
  })
  onMedia: boolean = true;

  currentUserId: string | undefined

  constructor(private authService: AuthService) {
    this.currentUserId = this.authService.user$.getValue()?.id;
  }

}
