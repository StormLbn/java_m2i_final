import { Component, Input } from '@angular/core';
import { Evaluation } from '../../models/Evaluation.model';
import { EvaluationService } from '../../services/evaluation.service';


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
  onMedia!: boolean;

  displayForm = false;

  @Input({
    required: true
  })
  currentUserId: string | undefined

  constructor(
    private evalService: EvaluationService
  ) {
  }

  onClickEdit() {
    this.displayForm = true;
    this.evalService.changeFormMode("edit");
    this.evalService.changeCurrentEval(this.evaluation);
  }

  onClickDelete() {
    this.displayForm = true;
    this.evalService.changeFormMode("delete");
    this.evalService.changeCurrentEval(this.evaluation);
  }

  closeForm() {
    this.displayForm = false;
  }
}
