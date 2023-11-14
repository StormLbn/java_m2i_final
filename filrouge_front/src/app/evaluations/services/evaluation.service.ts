import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evaluation } from '../models/Evaluation.model';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {

  private baseUrl = "http://localhost:8080/api/evaluation/";

  constructor(
    private http: HttpClient
  ) { }

  getEvaluationsForMedia(mediaId: string, page: number) {
    return this.http.get<Evaluation[]>(this.baseUrl + `media/${mediaId}/${page}`)
  }
  
  getEvaluationsForUser(userId: string, page: number) {
    return this.http.get<Evaluation[]>(this.baseUrl + `user/${userId}/${page}`)
  }
}
