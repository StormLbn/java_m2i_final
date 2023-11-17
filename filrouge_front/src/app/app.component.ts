import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { GenreService } from "./medias/services/genre.service";
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './auth/models/User.model';
import { MediaType } from './medias/models/MediaDetail.models';
import {MediaService} from "./medias/services/media.service";
import {MediaSummary} from "./medias/models/MediaSummary.model";
import {PageResponse} from "./global/models/PageResponse.model";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User | null = null;
  genres: string[] = [];
  selectedGenre: string | null = "";
  selectedType: MediaType | null = null;
  currentPage: number;
  mediaList: PageResponse<MediaSummary> | null = null;

  searchTerm: string | null = "";


  constructor(
    private authService: AuthService,
    private genreService: GenreService,
    private route: ActivatedRoute,
    private mediaService: MediaService,
    private router: Router
  ) {
    this.currentPage = +(this.route.snapshot.queryParamMap.get("page") ?? 0)
    this.selectedGenre = this.route.snapshot.queryParamMap.get("filter");
    this.selectedType = this.route.snapshot.queryParamMap.get("type") as MediaType;
    this.searchTerm = this.route.snapshot.queryParamMap.get("search");
  }

  ngOnInit(): void {
    this.genreService.getAllGenres().subscribe((data) => {
      this.genres = data;
    });

    this.authService.user$.subscribe(data => this.currentUser = data);
  }

  onClickLogout() {
    this.authService.logOut();
  }

  onGenreChange(): void {
    this.selectedType = null;
    this.searchTerm = null;
    this.navigateWithParams();
  }

  onTypeChange(type: MediaType): void {
    this.selectedGenre = null;
    this.selectedType = type;
    this.searchTerm = null;
    this.navigateWithParams();
  }

  navigateWithParams() {
    this.router.navigate(['/'], {queryParams: {
      page: 0,
      type: this.selectedType,
      filter: this.selectedGenre,
      search: this.searchTerm
    }});
  }git

  onTitleSearch(): void {
    this.selectedGenre = null;
    this.selectedType = null;
    this.navigateWithParams();

  }

  private loadRecommendedMedia(userId: string): void {
    this.mediaService.getRecommendedMedia(userId).subscribe((data) => {
      this.mediaList = data;
    });
  }

}
