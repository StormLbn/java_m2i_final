// media-list.component.ts

import { Component, OnInit } from '@angular/core';
import { MediaService } from '../../services/media.service';
import MediaSummaryDTO from "../../models/mediaSummaryDto.models";
import mediaSummaryDtoModels from "../../models/mediaSummaryDto.models";

@Component({
  selector: 'app-media-list',
templateUrl: 'media-list.component.html',
  styleUrls: ['./media-list.component.css'],
})
export class MediaListComponent implements OnInit {
    mediaList: MediaSummaryDTO[] = [];



    constructor(private mediaService: MediaService) {}

    ngOnInit(): void {
        this.mediaService.mediaList$.subscribe((data: MediaSummaryDTO[]) => {
            this.mediaList = data;

        });
    }



  protected readonly mediaSummaryDtoModels = mediaSummaryDtoModels;
}
