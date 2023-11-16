import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { GenreService } from "./medias/services/genre.service";
import { MediaService } from "./medias/services/media.service";
import { PageResponse } from './global/models/PageResponse.model';
import { MediaSummary } from './medias/models/MediaSummary.model';
import { Router } from '@angular/router';
import { User } from './auth/models/User.model';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  genres: string[] = [];
  selectedGenreName: string = "";
  mediaList: PageResponse<MediaSummary> | null = null;
  currentUser: User | null = null;
  selectedTypeName: string | null = null;


  constructor(
    private authService: AuthService,
    private genreService: GenreService,
    private mediaService: MediaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.genreService.getAllGenres().subscribe((data) => {
      this.genres = data;
    });

    this.mediaService.mediaPage$.subscribe((data) => {
      this.mediaList = data;
    });

    this.authService.user$.subscribe(data => {this.currentUser = data; console.log(this.currentUser);
    });
  }

  onClickLogout() {
    this.authService.logOut();
  }

  onGenreChange(): void {
    if (this.selectedGenreName !== "") {
      this.mediaService.getMediaByGenre(this.selectedGenreName);
    } else {
      this.mediaService.getAllMedia();
    }
    this.router.navigate(['/']);
  }
  onTypeChange(type: string): void {
    this.selectedGenreName = ""; // Clear selected genre when changing type
    this.selectedTypeName = type;
    this.mediaService.getMediaByType(type);
  }

}
