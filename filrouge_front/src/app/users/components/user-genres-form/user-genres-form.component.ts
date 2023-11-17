import { Component, Input, OnInit } from '@angular/core';
import { GenreService } from 'src/app/medias/services/genre.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-genres-form',
  templateUrl: './user-genres-form.component.html',
  styleUrls: ['./user-genres-form.component.css']
})
export class UserGenresFormComponent implements OnInit {

  @Input({
    required: true
  })
  authenticated = false;

  @Input({
    required: true
  })
  userGenresList!: string[];

  genresList: string[] = [];

  newGenres: string[] = [];

  editMode = false;

  constructor(
    private genreService: GenreService
  ) {
  }

  ngOnInit(): void {

    this.genresList = [...this.userGenresList];
  }

  onClickEdit() {
    this.editMode = true;
    this.genreService.getAllGenres().subscribe(data => this.genresList = data);
  }

  onClickCancel() {
    this.editMode = false;
    this.genresList = [...this.userGenresList];
  }

  onSubmit() {
    console.log(this.newGenres);
    
  }
}
