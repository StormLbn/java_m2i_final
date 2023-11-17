import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

import { UserProfilePageComponent } from './users/pages/user-profile-page/user-profile-page.component';
import { UserDetailFormComponent } from './users/components/user-detail-form/user-detail-form.component';
import { UserGenresFormComponent } from './users/components/user-genres-form/user-genres-form.component';
import { EditPasswordFormComponent } from './users/components/edit-password-form/edit-password-form.component';
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

registerLocaleData(localeFr, 'fr')

@NgModule({
  declarations: [
    AppComponent,
    UserProfilePageComponent,
    UserDetailFormComponent,
    UserGenresFormComponent,
    EditPasswordFormComponent,
    MediaHomePageComponent,
    MediaThumbnailComponent,
    MediaDetailComponent,
    MediaDetailPageComponent,
    ProfessionalListComponent,
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
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
