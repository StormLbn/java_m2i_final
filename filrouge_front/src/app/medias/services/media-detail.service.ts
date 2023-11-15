import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import MediaSummaryDTO from "../models/mediaSummaryDto.models";

@Injectable({
  providedIn: 'root',
})
export class MediaDetailService {
  private baseUrl = 'http://localhost:8080/api/media';

  constructor(private http: HttpClient) {}

  getMediaDetail(mediaId: string): Observable<any> {
    const url = `${this.baseUrl}/${mediaId}`;
    return this.http.get<any>(url)

        .pipe(

                  map((data: any) => {
                        return {
                          ...data,
                          type: data.type === "MOVIE" ? "Film" : "Série"
                        };
                      }
                  )

    );
  }

  // getMediaDetail1(): Observable<any[]> {
  //   return this.http.get<any[]>(this.apiUrl)
  //       .pipe(
  //           map((data: any[]) => {
  //                 const newArray = [] as any[]
  //
  //                 for (const element of data) {
  //                   newArray.push({
  //                     ...element,
  //                     type: element.type === "MOVIE" ? "Film" : "Série"
  //                   });
  //                 }
  //
  //                 return newArray;
  //               }
  //           )
  //       );
  //
  //
  // }
}
