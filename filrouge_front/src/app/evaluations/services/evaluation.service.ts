import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  getEvaluationsForMedia(mediaId: string, page: number) {
    this.http.get<PageResponse<Evaluation>>(this.baseUrl + `media/${mediaId}/${page}`).subscribe(data => this.evaluations$.next(data));
  }
  
  getEvaluationsForUser(userId: string, page: number) {
    this.http.get<PageResponse<Evaluation>>(this.baseUrl + `user/${userId}/${page}`).subscribe(data => this.evaluations$.next(data));
  }

  addEvaluation(evaluation: Evaluation) {
    console.log("Ajout évaluation");
    
    const currentUser = this.authService.user$.getValue();
    if (currentUser) {
      console.log("Utilisateur trouvé");
      
      const newEval: Evaluation = {
        ...evaluation,
        userId: currentUser.id
      }
      console.log("évaluation");
      console.log(newEval);
      
      const httpOptions = {
        headers: new HttpHeaders({
          'Access-Control-Allow-Origin':'*',
          'Access-Control-Allow-Headers': "*",
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.authService.getToken(),
        })
      };
      console.log("Header");
      console.log(httpOptions);

      this.http.post<Evaluation>(this.baseUrl + "add", newEval, httpOptions).subscribe();
    } else {
      console.log("Utilisateur non trouvé");
    }
  }

  editEvaluation(evaluation: Evaluation) {
    console.log("Modification eval");
    
    console.log(evaluation);
    
    this.http.patch<Evaluation>(this.baseUrl + evaluation.id, evaluation).subscribe();
  }

  deleteEvaluation(evaluation: Evaluation) {
    console.log("Suppression éval");
    
    console.log(evaluation);
    
    this.http.delete<Evaluation>(this.baseUrl + evaluation.id).subscribe();
  }

}

export { FormMode };