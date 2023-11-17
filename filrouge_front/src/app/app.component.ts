import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { GenreService } from "./medias/services/genre.service";
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './auth/models/User.model';
import { MediaType } from './medias/models/MediaDetail.models';
import { MediaSummary } from './medias/models/MediaSummary.model';
import { RecommendationService } from './users/services/recommendation.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: User | null = null;
  userRecommendations: MediaSummary[] = []

  genres: string[] = [];
  selectedGenre: string | null = "";
  selectedType: MediaType | null = null;
  currentPage: number;
  searchTerm: string | null = "";

  constructor(
    private authService: AuthService,
    private genreService: GenreService,
    private route: ActivatedRoute,
    private recommendationService: RecommendationService,
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

    this.authService.user$.subscribe(data => {
      this.currentUser = data;
      if (this.currentUser) {
        this.recommendationService.getRecommendationsForUser(this.currentUser.id);
      }
    });
  }

  onClickLogout() {
    this.recommendationService.recommendations$.next([]);
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

  onTitleSearch(): void {
    this.selectedGenre = null;
    this.selectedType = null;
    this.navigateWithParams();

  }

  navigateWithParams() {
    this.router.navigate(['/'], {
      queryParams: {
        page: 0,
        type: this.selectedType,
        filter: this.selectedGenre,
        search: this.searchTerm
      }
    });
  }

}
