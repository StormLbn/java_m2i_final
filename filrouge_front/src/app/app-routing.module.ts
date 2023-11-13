// app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';
import {MediaDetailComponent} from "./medias/components/media-detail/media-detail.component";

const routes: Routes = [
  { path: 'media-home', component: MediaHomePageComponent },
  { path: 'media/:id', component: MediaDetailComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
//dfsegszgsgg
