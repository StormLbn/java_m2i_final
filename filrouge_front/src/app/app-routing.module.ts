import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationPageComponent } from './auth/pages/authentication-page/authentication-page.component';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';
import { MediaDetailPageComponent } from './medias/pages/media-detail-page/media-detail-page.component';

const routes: Routes = [
  { path: 'auth', component: AuthenticationPageComponent },
  { path: '', component: MediaHomePageComponent },
  { path: 'media/:id', component: MediaDetailPageComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
