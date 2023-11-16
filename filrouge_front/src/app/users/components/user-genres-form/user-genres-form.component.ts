import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-user-genres-form',
  templateUrl: './user-genres-form.component.html',
  styleUrls: ['./user-genres-form.component.css']
})
export class UserGenresFormComponent {

  @Input({
    required: true
  })
  genresList!: string[];

  @Input({
    required: true
  })
  authenticated = false;

  constructor() {

  }
}
