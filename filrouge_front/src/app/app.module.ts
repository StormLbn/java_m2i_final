// app.module.ts

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MediaHomePageComponent } from './medias/pages/media-home-page/media-home-page.component';
import { MediaService } from './medias/services/media.service';
import { MediaListComponent } from './medias/components/media-list/media-list.component';
import {HttpClientModule} from "@angular/common/http";
import { MediaDetailComponent } from './medias/components/media-detail/media-detail.component';
import { MediaDetailPageComponent } from './medias/pages/media-detail-page/media-detail-page.component';
import { ProfessionalListComponent } from './medias/components/professional-list/professional-list.component';

@NgModule({
  declarations: [
    AppComponent,
    MediaHomePageComponent,
    MediaListComponent,
    MediaDetailComponent,
    MediaDetailPageComponent,
    ProfessionalListComponent,
    ProfessionalListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
      HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
