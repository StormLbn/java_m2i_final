// genre.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GenreDTO } from '../models/genreDto.models';

@Injectable({
    providedIn: 'root',
})
export class GenreService {
    private baseUrl = 'http://localhost:8080/api/genre';

    constructor(private http: HttpClient) {}

    getAllGenres(): Observable<GenreDTO[]> {
        const url = `${this.baseUrl}/all`;
        return this.http.get<GenreDTO[]>(url);
    }
}
