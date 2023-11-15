import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { MediaType } from "../enums/media-type";
import MediaSummaryDTO from "../models/mediaSummaryDto.models";
import { PageResponse } from 'src/app/global/models/PageResponse.model';

@Injectable({
    providedIn: 'root',
})
export class MediaService {
    private apiUrl = 'http://localhost:8080/api/media';

    mediaPage$ = new BehaviorSubject<PageResponse<MediaSummaryDTO> | null>(null)

    constructor(private http: HttpClient) {
        this.getAllMedia();
    }

    getAllMedia() {
        this.http.get<PageResponse<MediaSummaryDTO>>(`${this.apiUrl}/all/0`)
            // .pipe(
            //     map((data) => {
            //         const newArray = [] as MediaSummaryDTO[]

            //         for (const element of data.content) {
            //             newArray.push({
            //                 ...element,
            //                 type: element.type === "MOVIE" ? "Film" : "Série"
            //             });
            //         }

            //         return newArray;
            //     })
            // )
            .subscribe((value => this.mediaPage$.next(value)));


    }

    getMediaByGenre(genreName: string) {
        const apiUrlByGenre = `${this.apiUrl}/all/genre/${genreName}/0`;

        this.http.get<PageResponse<MediaSummaryDTO>>(apiUrlByGenre)
        // .pipe(
        //     map((data: any[]) => {
        //         const newArray = [] as MediaSummaryDTO[];

        //         for (const element of data) {
        //             newArray.push({
        //                 ...element,
        //                 type: element.type === 'MOVIE' ? 'Film' : 'Série',
        //             });
        //         }

        //         return newArray;
        //     })
        // )
        .subscribe(data => this.mediaPage$.next(data));
    }

}

