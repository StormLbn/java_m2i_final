import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationPageComponent } from './pages/authentication-page/authentication-page.component';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';

const routes: Routes = [
  { path: 'auth', component: AuthenticationPageComponent },
  { path: 'media-home', component: MediaHomePageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
