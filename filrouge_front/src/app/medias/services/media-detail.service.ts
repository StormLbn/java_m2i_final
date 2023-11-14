import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MediaDetailService {
  private baseUrl = 'http://localhost:8080/api/media';

  constructor(private http: HttpClient) {}

  getMediaDetail(mediaId: string): Observable<any> {
    const url = `${this.baseUrl}/${mediaId}`;
    return this.http.get<any>(url);
  }


}
