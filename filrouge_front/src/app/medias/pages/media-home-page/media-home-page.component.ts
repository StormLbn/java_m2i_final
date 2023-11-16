import { Component } from '@angular/core';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { MediaSummary } from '../../models/MediaSummary.model';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-media-home-page',
  templateUrl: './media-home-page.component.html',
  styleUrls: ['./media-home-page.component.css']
})
export class MediaHomePageComponent {
  mediaPage: PageResponse<MediaSummary> | null = null;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
    this.mediaService.mediaPage$.subscribe((data) => {
      this.mediaPage = data;
    });
  }
}
