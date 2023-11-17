import { Component } from '@angular/core';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { MediaSummary } from '../../models/MediaSummary.model';
import { MediaService } from '../../services/media.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { MediaType } from '../../models/MediaDetail.models';
import { RecommendationService } from 'src/app/users/services/recommendation.service';

@Component({
  selector: 'app-media-home-page',
  templateUrl: './media-home-page.component.html',
  styleUrls: ['./media-home-page.component.css']
})
export class MediaHomePageComponent {
  mediaPage: PageResponse<MediaSummary> | null = null;
  genre: string | null = "";
  mediaType: MediaType | null = null;
  currentPage: number = 0;
  searchTerm: string | null = "";

  recommendations: MediaSummary[] = [];

  constructor(
    private mediaService: MediaService,
    private recommendationsService: RecommendationService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.queryParamMap.subscribe((params: ParamMap) => {
      this.currentPage = +(params.get("page") ?? 0);
      this.genre = params.get("filter");
      this.mediaType = params.get("type") as MediaType;
      this.searchTerm = params.get("search");
      this.mediaService.getMediaPage({
        pageNumber: this.currentPage,
        filter: this.genre ?? "",
        mediaType: this.mediaType,
        search: this.searchTerm
      });

      window.scrollTo({top: 0, left: 0, behavior:'smooth'});
    });

    this.mediaService.mediaPage$.subscribe((data) => {
      this.mediaPage = data;
    });

    this.recommendationsService.recommendations$.subscribe(data => {this.recommendations = data});
  }

  onClickPrevious() {
    if (this.mediaPage && this.mediaPage.pageNumber > 0) {
      this.navigateToPage(this.currentPage - 1);
    }
  }

  onClickNext() {
    if (this.mediaPage && this.mediaPage.pageNumber < this.mediaPage.totalElements) {
      this.navigateToPage(this.currentPage + 1);
    }
  }

  navigateToPage(page: number) {
    this.router.navigate(['/'], {queryParams: {
      page: page,
      type: this.mediaType,
      filter: this.genre,
      search: this.searchTerm
    }});
  }
}
