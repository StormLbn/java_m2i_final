// media-list.component.ts

import { Component, OnInit } from '@angular/core';
import { MediaService } from '../../services/media.service';
import MediaSummaryDTO from "../../models/mediaSummaryDto.models";
import mediaSummaryDtoModels from "../../models/mediaSummaryDto.models";
import { PageResponse } from 'src/app/global/models/PageResponse.model';

@Component({
  selector: 'app-media-list',
  templateUrl: 'media-list.component.html',
  styleUrls: ['./media-list.component.css'],
})
export class MediaListComponent implements OnInit {
  mediaList: PageResponse<MediaSummaryDTO> | null = null;

  constructor(private mediaService: MediaService) { }

  ngOnInit(): void {
    this.mediaService.mediaPage$.subscribe((data) => {
      this.mediaList = data;
    });
  }

  // protected readonly mediaSummaryDtoModels = mediaSummaryDtoModels;
}
