// media.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MediaService {
  private apiUrl = 'http://localhost:8080/api/media/all';

  constructor(private http: HttpClient) {}

  getAllMedia(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
