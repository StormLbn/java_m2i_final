import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MediaDetail } from '../../models/MediaDetail.models';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-media-detail',
  templateUrl: './media-detail.component.html',
  styleUrls: ['./media-detail.component.css'],
})
export class MediaDetailComponent implements OnInit {
  mediaDetail: MediaDetail | undefined;

  constructor(
    private route: ActivatedRoute,
    private mediaService: MediaService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const mediaId = params.get('id');
      if (mediaId) {
        this.mediaService.getMediaDetailsById(mediaId).subscribe((data) => {
          this.mediaDetail = data;
        });
      }
    });
  }

}
