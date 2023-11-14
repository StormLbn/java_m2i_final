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
import { MediaService } from './medias/services/media.service';
import { MediaListComponent } from './medias/components/media-list/media-list.component';

@NgModule({
  declarations: [
    AppComponent,
    MediaHomePageComponent,
    MediaListComponent,
    AppComponent,
    AuthenticationPageComponent,
    LoginFormComponent,
    SignupFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [[MediaService]],
  bootstrap: [AppComponent]
})
export class AppModule { }
