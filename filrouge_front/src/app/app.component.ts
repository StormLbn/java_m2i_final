import { Component } from '@angular/core';
import { AuthService } from './auth/services/auth.service';
import { GenreService } from "./medias/services/genre.service";
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './auth/models/User.model';
import { MediaType } from './medias/models/MediaDetail.models';


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

  searchTerm: string = "";


  constructor(
    private authService: AuthService,
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.currentPage = +(this.route.snapshot.queryParamMap.get("page") ?? 0)
    this.selectedGenre = this.route.snapshot.queryParamMap.get("filter");
    this.selectedType = this.route.snapshot.queryParamMap.get("type") as MediaType;
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
    this.navigateWithParams();
  }

  onTypeChange(type: MediaType): void {
    this.selectedGenre = null;
    this.selectedType = type;
    this.navigateWithParams();
  }

  navigateWithParams() {
    this.router.navigate(['/'], {queryParams: {
      page: 0,
      type: this.selectedType,
      filter: this.selectedGenre
    }});
  }

  onTitleSearch(): void {
    // this.mediaService.searchMediaByTitle(this.searchTerm);

  }

}
