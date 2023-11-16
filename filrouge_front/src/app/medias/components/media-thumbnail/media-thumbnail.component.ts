import { Component, Input, OnInit } from '@angular/core';
import { MediaSummary } from '../../models/MediaSummary.model';

@Component({
  selector: 'app-media-thumbnail',
  templateUrl: 'media-thumbnail.component.html',
  styleUrls: ['./media-thumbnail.component.css'],
})
export class MediaThumbnailComponent {

  @Input({
    required: true
  })
  media!: MediaSummary
}
