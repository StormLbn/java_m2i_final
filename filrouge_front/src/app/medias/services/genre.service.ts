import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class GenreService {
    private baseUrl = 'http://localhost:8080/api/genre';

    constructor(private http: HttpClient) { }

    getAllGenres(): Observable<string[]> {
        return this.http.get<string[]>(`${this.baseUrl}/all`).pipe(
            map((data: any[]) => {
                const genres: string[] = [];
                for(let element of data) {
                    genres.push(element.genreName)
                }
                return genres;
            })
        );
    }
}
