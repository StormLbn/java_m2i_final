import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AuthenticationPageComponent } from './auth/pages/authentication-page/authentication-page.component';
import { LoginFormComponent } from './auth/components/login-form/login-form.component';
import { SignupFormComponent } from './auth/components/signup-form/signup-form.component';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';
import { MediaThumbnailComponent } from './medias/components/media-thumbnail/media-thumbnail.component';
import { EvaluationComponent } from './evaluations/components/evaluation/evaluation.component';
import { MediaDetailComponent } from './medias/components/media-detail/media-detail.component';
import { MediaDetailPageComponent } from './medias/pages/media-detail-page/media-detail-page.component';
import { ProfessionalListComponent } from './medias/components/professional-list/professional-list.component';
import { EvaluationListComponent } from './evaluations/components/evaluation-list/evaluation-list.component';
import { EvaluationFormComponent } from './evaluations/components/evaluation-form/evaluation-form.component';
import { MediaTypePipe } from './medias/pipes/media-type.pipe';
import { RecommandationComponent } from './global/recommandation/recommandation.component';

@NgModule({
  declarations: [
    AppComponent,
    MediaHomePageComponent,
    MediaThumbnailComponent,
    MediaDetailComponent,
    MediaDetailPageComponent,
    ProfessionalListComponent,
    ProfessionalListComponent,
    AppComponent,
    AuthenticationPageComponent,
    LoginFormComponent,
    SignupFormComponent,
    EvaluationComponent,
    EvaluationListComponent,
    EvaluationFormComponent,
    MediaTypePipe,
    RecommandationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
