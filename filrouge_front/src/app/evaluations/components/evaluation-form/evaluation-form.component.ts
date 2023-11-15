import { Component, EventEmitter, Input, Output } from '@angular/core';
import { EvaluationService, FormMode } from '../../services/evaluation.service';
import { Subscription } from 'rxjs';
import { Evaluation } from '../../models/Evaluation.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-evaluation-form',
  templateUrl: './evaluation-form.component.html',
  styleUrls: ['./evaluation-form.component.css']
})
export class EvaluationFormComponent {

  mediaId: string;

  formMode: FormMode = null;
  formModeSub: Subscription | undefined;

  currentEval: Evaluation;
  evaluation: Evaluation;

  @Output()
  closeFormEvent = new EventEmitter();

  constructor(
    private evalService: EvaluationService,
    private route: ActivatedRoute
  ) {
    this.mediaId = this.route.snapshot.params["id"];

    this.formModeSub = this.evalService.formMode$.subscribe(mode => this.formMode = mode);
    this.currentEval = this.evalService.currentEval$.getValue() ?? {
      comment: "",
      rating: null,
      mediaId: this.mediaId
    };
    this.evaluation = {...this.currentEval};
  }

  onSubmitForm() {
    console.log("clic !");
    
    switch (this.formMode) {
      case "add" :
        this.evalService.addEvaluation(this.evaluation);
        break;
      case "edit" :
        this.evalService.editEvaluation(this.evaluation);
        break;
      case "delete" :
        this.evalService.deleteEvaluation(this.evaluation);
        break;
    }
    this.closeFormEvent.emit();
  }

  onClickCancel() {
    this.closeFormEvent.emit();
  }
}
