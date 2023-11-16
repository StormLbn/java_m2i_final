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
  evalPage: number;

  formMode: FormMode = null;
  formModeSub: Subscription | undefined;

  currentEval: Evaluation;
  evaluation: Evaluation;

  @Input()
  onMedia = true;

  @Output()
  closeFormEvent = new EventEmitter();

  constructor(
    private evalService: EvaluationService,
    private route: ActivatedRoute
  ) {
    this.mediaId = this.route.snapshot.params["id"];
    this.evalPage = +(this.route.snapshot.queryParamMap.get("evalPage") ?? 0);

    this.formModeSub = this.evalService.formMode$.subscribe(mode => this.formMode = mode);
    this.currentEval = this.evalService.currentEval$.getValue() ?? {
      comment: "",
      rating: null,
      mediaId: this.mediaId,
      userId: ""
    };
    this.evaluation = { ...this.currentEval };
  }

  onSubmitForm() {
    switch (this.formMode) {
      case "add":
        this.evalService.addEvaluation(this.evaluation);
        break;
      case "edit":
        this.evalService.editEvaluation(this.evaluation, this.evalPage, this.onMedia);
        break;
      case "delete":
        this.evalService.deleteEvaluation(this.evaluation, this.onMedia);
        break;
    }
    this.closeFormEvent.emit();
  }

  onClickCancel() {
    this.closeFormEvent.emit();
  }
}
