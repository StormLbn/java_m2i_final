// genre.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class GenreService {
    private baseUrl = 'http://localhost:8080/api/genre';

    constructor(private http: HttpClient) { }

    getAllGenres(): Observable<string[]> {
        return this.http.get<string[]>(`${this.baseUrl}/all`);
    }
}
