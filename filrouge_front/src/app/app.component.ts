import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { GenreService } from "./medias/services/genre.service";
import MediaSummaryDTO from "./medias/models/mediaSummaryDto.models";
import { MediaService } from "./medias/services/media.service";
import { PageResponse } from './global/models/PageResponse.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  genres: string[] = [];
  selectedGenreName: string = "";
  mediaList: PageResponse<MediaSummaryDTO> | null = null;


  constructor(
    private authService: AuthService,
    private genreService: GenreService,
    private mediaService: MediaService
  ) { }

  ngOnInit(): void {
    this.genreService.getAllGenres().subscribe((data) => {
      console.log(data);
      
      this.genres = data;
    });
  }

  onClickLogout() {
    this.authService.logOut();
  }

  onGenreChange(): void {
    if (this.selectedGenreName !== "") {
      this.mediaService.getMediaByGenre(this.selectedGenreName);
    } else {
      this.loadAllMedia();
    }
  }

  private loadAllMedia(): void {
    this.mediaService.mediaPage$.subscribe((data) => {
      this.mediaList = data;
    });
  }

}
