import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserProfilePageComponent } from './pages/user-profile-page/user-profile-page.component';
import { RecommendationListComponent } from './components/user/recommendation-list/recommendation-list.component';
import { UserDetailFormComponent } from './components/user/user-detail-form/user-detail-form.component';
import { UserGenresFormComponent } from './components/user/user-genres-form/user-genres-form.component';
import { EditPasswordFormComponent } from './components/user/edit-password-form/edit-password-form.component';

@NgModule({
  declarations: [
    AppComponent,
    UserProfilePageComponent,
    RecommendationListComponent,
    UserDetailFormComponent,
    UserGenresFormComponent,
    EditPasswordFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
