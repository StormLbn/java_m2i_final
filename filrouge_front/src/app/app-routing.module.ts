// app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';

const routes: Routes = [
  { path: 'media-home', component: MediaHomePageComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
