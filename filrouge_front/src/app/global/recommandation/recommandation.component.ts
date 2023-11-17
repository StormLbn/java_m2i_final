import { Component, Input } from '@angular/core';
import { MediaSummary } from 'src/app/medias/models/MediaSummary.model';

@Component({
  selector: 'app-recommandation',
  templateUrl: './recommandation.component.html',
  styleUrls: ['./recommandation.component.css']
})
export class RecommandationComponent {

  @Input({
    required: true
  })
  mediaList!: MediaSummary[];

  constructor() {}
}
