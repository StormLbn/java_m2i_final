import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Evaluation } from 'src/app/evaluations/components/models/Evaluation.model';
import { EvaluationService } from 'src/app/evaluations/services/evaluation.service';

@Component({
  selector: 'app-media-detail-page',
  templateUrl: './media-detail-page.component.html',
  styleUrls: ['./media-detail-page.component.css']
})
export class MediaDetailPageComponent {

  evaluations: Evaluation[] = [];
  mediaId: string;

  constructor(
    private evalService: EvaluationService,
    private route: ActivatedRoute
  ) {
    console.log("constructor");
    
    this.mediaId = this.route.snapshot.params["id"];
    console.log(this.mediaId);
    
    this.evalService.getEvaluationsForMedia(this.mediaId, 0).subscribe(data => this.evaluations = data);
    
  }

}
