// media-detail.component.ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MediaDetailService } from '../../services/media-detail.service';
import { MediaDetailDTO } from '../../models/mediaDetailDto.models';

@Component({
  selector: 'app-media-detail',
  templateUrl: './media-detail.component.html',
  styleUrls: ['./media-detail.component.css'],
})
export class MediaDetailComponent implements OnInit {
  mediaDetail: MediaDetailDTO | undefined;

  constructor(
    private route: ActivatedRoute,
    private mediaDetailService: MediaDetailService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const mediaId = params.get('id');
      if (mediaId) {
        this.fetchMediaDetail(mediaId);
      }
    });
  }

  fetchMediaDetail(id: string): void {
    this.mediaDetailService.getMediaDetail(id).subscribe((data) => {
      this.mediaDetail = data;
      console.log(data);
    });
  }
}
