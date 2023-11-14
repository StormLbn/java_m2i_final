import { Component, Input } from '@angular/core';
import { Evaluation } from '../../models/Evaluation.model';

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

}
