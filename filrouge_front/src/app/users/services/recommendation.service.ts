import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { MediaSummary } from 'src/app/medias/models/MediaSummary.model';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  private baseUrl = 'http://localhost:8080/api/recommendations/'

  recommendations$ = new BehaviorSubject<MediaSummary[]>([]);

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }
  
  getRecommendationsForUser(userId: string): void {
    this.http
      .get<MediaSummary[]>(this.baseUrl + userId, {headers: this.authService.generateHeaders()})
      .subscribe(data => this.recommendations$.next(data));
  }

}
