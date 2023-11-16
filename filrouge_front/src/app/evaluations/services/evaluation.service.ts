import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evaluation } from '../models/Evaluation.model';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';

type FormMode = "add" | "edit" | "delete" | null;

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  private baseUrl = "http://localhost:8080/api/evaluation/";

  evaluations$ = new BehaviorSubject<PageResponse<Evaluation> | null>(null);

  currentEval$ = new BehaviorSubject<Evaluation | null>(null);

  formMode$ = new BehaviorSubject<FormMode | null>(null);

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  changeFormMode(mode: FormMode) {
    this.formMode$.next(mode);
  }

  changeCurrentEval(evaluation: Evaluation | null) {
    this.currentEval$.next(evaluation);
  }

  getEvaluationsForMedia(mediaId: string, page: number = 0) {
    this.http.get<PageResponse<Evaluation>>(`${this.baseUrl}media/${mediaId}/${page}`).subscribe(data => this.evaluations$.next({ ...data }));
  }

  getEvaluationsForUser(userId: string, page: number) {
    this.http
      .get<PageResponse<Evaluation>>(`${this.baseUrl}user/${userId}/${page}`, {headers: this.authService.generateHeaders()})
      .subscribe(data => this.evaluations$.next({ ...data }));
  }

  addEvaluation(evaluation: Evaluation) {
    const currentUser = this.authService.user$.getValue();
    if (currentUser) {
      const newEval: Evaluation = {
        ...evaluation,
        userId: currentUser.id
      }

      this.http
        .post<Evaluation>(this.baseUrl + "add", newEval, { headers: this.authService.generateHeaders() })
        .subscribe(() => this.getEvaluationsForMedia(newEval.mediaId, 0));
    }
  }

  editEvaluation(evaluation: Evaluation, page: number = 0, onMedia: boolean) {
    this.http
      .patch<Evaluation>(this.baseUrl + evaluation.id, evaluation, { headers: this.authService.generateHeaders() })
      .subscribe(() => {
        if (onMedia) {
          this.getEvaluationsForMedia(evaluation.mediaId, page)
        } else {
          this.getEvaluationsForUser(evaluation.userId, page)
        }
      });
  }

  deleteEvaluation(evaluation: Evaluation, onMedia: boolean) {
    this.http
      .delete<string>(this.baseUrl + evaluation.id, {
        headers: this.authService.generateHeaders(),
        responseType: 'text' as 'json'
      })
      .subscribe(() => {
        if (onMedia) {
          this.getEvaluationsForMedia(evaluation.mediaId, 0)
        } else {
          this.getEvaluationsForUser(evaluation.userId, 0)
        }
      });
  }
}

export { FormMode };