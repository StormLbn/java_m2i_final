import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import {GenreDTO} from "./medias/models/genreDto.models";
import {GenreService} from "./medias/services/genre.service";
import MediaSummaryDTO from "./medias/models/mediaSummaryDto.models";
import {MediaService} from "./medias/services/media.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  genres: GenreDTO[] = [];
  selectedGenreName: string | null = null;
  mediaList: MediaSummaryDTO[] = [];


  constructor(
    private authService: AuthService,
    private genreService: GenreService,
    private mediaService: MediaService
) {}

ngOnInit(): void {
  this.genreService.getAllGenres().subscribe((data) => {
    this.genres = data;

  });
}
    onClickLogout() {
      this.authService.logOut();
    }



  onGenreChange(): void {
    if (this.selectedGenreName !== null) {
      this.mediaService.getMediaByGenre(this.selectedGenreName);
    } else {

      this.loadAllMedia();
    }
  }

  private loadAllMedia(): void {
    this.mediaService.mediaList$.subscribe((data) => {
      this.mediaList = data;
    });
  }



}
