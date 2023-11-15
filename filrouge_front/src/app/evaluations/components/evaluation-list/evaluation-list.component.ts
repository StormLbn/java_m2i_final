import { Component } from '@angular/core';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { Evaluation } from '../../models/Evaluation.model';
import { EvaluationService } from '../../services/evaluation.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';

@Component({
  selector: 'app-evaluation-list',
  templateUrl: './evaluation-list.component.html',
  styleUrls: ['./evaluation-list.component.css']
})
export class EvaluationListComponent {

  evaluationsPage: PageResponse<Evaluation> | null = null;
  mediaId: string;
  pageNumber = 0;

  constructor(
    private evalService: EvaluationService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    console.log("constructor");
    
    this.mediaId = this.route.snapshot.params["id"];
    console.log(this.mediaId);

    this.route.queryParamMap.subscribe((params: ParamMap) => {
      this.pageNumber = +(params.get("evalPage") ?? 0);
      this.evalService.getEvaluationsForMedia(this.mediaId, this.pageNumber);
    })
    
    this.evalService.evaluations$.subscribe(data => this.evaluationsPage = data);
  }

  onClickPrevious() {
    if (this.evaluationsPage && this.evaluationsPage.pageNumber > 0) {
      this.router.navigate([], {queryParams: {"evalPage": --this.pageNumber}})
    }
  }

  onClickNext() {
    if (this.evaluationsPage && this.evaluationsPage.pageNumber < this.evaluationsPage.totalElements) {
      this.router.navigate([], {queryParams: {"evalPage": ++this.pageNumber}})
    }
  }
}
