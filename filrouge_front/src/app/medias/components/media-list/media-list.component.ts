import { Component, OnInit } from '@angular/core';
import { MediaService } from '../../services/media.service';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { MediaSummary } from '../../models/MediaSummary.model';

@Component({
  selector: 'app-media-list',
  templateUrl: 'media-list.component.html',
  styleUrls: ['./media-list.component.css'],
})
export class MediaListComponent implements OnInit {
  mediaPage: PageResponse<MediaSummary> | null = null;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
    this.mediaService.mediaPage$.subscribe((data) => {
      this.mediaPage = data;
    });
  }
}
