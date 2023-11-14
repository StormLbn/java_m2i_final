import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {BehaviorSubject, map, Observable} from 'rxjs';
import {MediaType} from "../enums/media-type";
import MediaSummaryDTO from "../models/mediaSummaryDto.models";

@Injectable({
  providedIn: 'root',
})
export class MediaService {
  private apiUrl = 'http://localhost:8080/api/media/all';

  mediaList$ = new BehaviorSubject<MediaSummaryDTO[]>([])

  constructor(private http: HttpClient) {
      this.getAllMedia();
  }

  getAllMedia(){
    this.http.get<any[]>(this.apiUrl)
        .pipe(
            map((data: any[]) => {
                const newArray = [] as MediaSummaryDTO[]

                for (const element of data) {
                  newArray.push({
                      ...element,
                      type: element.type === "MOVIE" ? "Film" : "Série"
                  });
                }

                return newArray;
          }
      )
    ).subscribe((value => this.mediaList$.next(value)));


  }
    getMediaByGenre(genreName: string) {
        const apiUrlByGenre = `http://localhost:8080/api/media/all/genre/${genreName}/0`;

        this.http.get<any[]>(apiUrlByGenre).pipe(
            map((data: any[]) => {
                const newArray = [] as MediaSummaryDTO[];

                for (const element of data) {
                    newArray.push({
                        ...element,
                        type: element.type === 'MOVIE' ? 'Film' : 'Série',
                    });
                }

                return newArray;
            })
        ).subscribe(data => this.mediaList$.next(data));
    }

}

