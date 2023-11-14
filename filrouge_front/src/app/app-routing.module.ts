import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserProfilePageComponent } from './users/pages/user-profile-page/user-profile-page.component';
import { AuthenticationPageComponent } from './auth/pages/authentication-page/authentication-page.component';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';
import { MediaDetailPageComponent } from './medias/pages/media-detail-page/media-detail-page.component';

const routes: Routes = [
  { path: '', component: MediaHomePageComponent },
  { path: 'auth', component: AuthenticationPageComponent },
  { path: 'media/:id', component: MediaDetailPageComponent },
  { path: 'user/:id', component: UserProfilePageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
