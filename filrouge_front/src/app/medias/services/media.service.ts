import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { MediaSummary } from '../models/MediaSummary.model';
import { MediaDetail } from '../models/MediaDetail.models';

@Injectable({
    providedIn: 'root',
})
export class MediaService {
    private baseUrl = 'http://localhost:8080/api/media';

    mediaPage$ = new BehaviorSubject<PageResponse<MediaSummary> | null>(null)

    constructor(private http: HttpClient) {
        this.getAllMedia();
    }

    getMediaDetailsById(mediaId: string) {
        return this.http.get<MediaDetail>(`${this.baseUrl}/${mediaId}`);
    }

    getAllMedia(page: number = 0) {
        this.http.get<PageResponse<MediaSummary>>(`${this.baseUrl}/all/${page}`)
            .subscribe((value => this.mediaPage$.next(value)));
    }

    getMediaByGenre(genreName: string, page: number = 0) {
        const apiUrlByGenre = `${this.baseUrl}/all/genre/${genreName}/${page}`;

        this.http.get<PageResponse<MediaSummary>>(apiUrlByGenre)
            .subscribe(data => this.mediaPage$.next(data));
    }

    getMediaByType(type: string, page: number = 0) {
      const apiUrlByType = `${this.baseUrl}/all/type/${type}/${page}`;

      this.http.get<PageResponse<MediaSummary>>(apiUrlByType)
        .subscribe(data => this.mediaPage$.next(data));
    }

}

