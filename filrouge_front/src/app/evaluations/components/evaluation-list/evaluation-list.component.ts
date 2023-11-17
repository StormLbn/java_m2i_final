import { Component, Input, OnInit } from '@angular/core';
import { PageResponse } from 'src/app/global/models/PageResponse.model';
import { Evaluation } from '../../models/Evaluation.model';
import { EvaluationService } from '../../services/evaluation.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-evaluation-list',
  templateUrl: './evaluation-list.component.html',
  styleUrls: ['./evaluation-list.component.css']
})
export class EvaluationListComponent implements OnInit {

  evaluationsPage: PageResponse<Evaluation> | null = null;
  mediaId: string;
  pageNumber = 0;
  displayForm = false;
  currentUserId: string | undefined = undefined;
  
  @Input({
    required: true
  })
  onMedia = true;

  constructor(
    private evalService: EvaluationService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {
    this.mediaId = this.route.snapshot.params["id"];
  }

  ngOnInit(): void {
    this.authService.user$.subscribe(data => this.currentUserId = data?.id)

    this.route.queryParamMap.subscribe((params: ParamMap) => {
      this.pageNumber = +(params.get("evalPage") ?? 0);
      
      if (this.onMedia) {
        this.evalService.getEvaluationsForMedia(this.mediaId, this.pageNumber);
      } else {
        this.evalService.getEvaluationsForUser(this.mediaId, this.pageNumber);
      }
    })

    this.evalService.evaluations$.subscribe(data => this.evaluationsPage = data);
  }

  onClickAdd() {
    this.displayForm = true;
    this.evalService.changeFormMode("add");
    this.evalService.changeCurrentEval(null);
    this.evalService.getEvaluationsForMedia(this.mediaId, this.pageNumber);
  }

  onClickPrevious() {
    if (this.evaluationsPage && this.evaluationsPage.pageNumber > 0) {
      this.router.navigate([], { queryParams: { "evalPage": --this.evaluationsPage.pageNumber } })
    }
  }

  onClickNext() {
    if (this.evaluationsPage && this.evaluationsPage.pageNumber < this.evaluationsPage.totalElements) {
      this.router.navigate([], { queryParams: { "evalPage": ++this.evaluationsPage.pageNumber } })
    }
  }

  closeForm() {
    this.displayForm = false;
  }
}
