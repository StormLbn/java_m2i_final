import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evaluation } from '../models/Evaluation.model';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  private baseUrl = "http://localhost:8080/api/evaluation/";

  evaluations$ = new BehaviorSubject<PageResponse<Evaluation> | null>(null);

  constructor(
    private http: HttpClient
  ) { }

  getEvaluationsForMedia(mediaId: string, page: number) {
    this.http.get<PageResponse<Evaluation>>(this.baseUrl + `media/${mediaId}/${page}`).subscribe(data => this.evaluations$.next(data));
  }
  
  getEvaluationsForUser(userId: string, page: number) {
    this.http.get<PageResponse<Evaluation>>(this.baseUrl + `user/${userId}/${page}`).subscribe(data => this.evaluations$.next(data));
  }
}
