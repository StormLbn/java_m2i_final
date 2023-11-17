import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { FormType, UserService } from '../../services/user.service';
import { User } from 'src/app/auth/models/User.model';

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.css']
})
export class UserProfilePageComponent {

  user: User;
  authenticated = false;
  form: FormType = null;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private authService: AuthService
  ) {
    const blankUser: User = {
      id: "",
      pseudo: "",
      mail: "",
      birthDate: new Date(),
      genres: []
    };
    const userId = this.route.snapshot.params["id"];
    this.userService.getUserById(userId);

    this.user = blankUser;
    this.userService.user$.subscribe(data => {
      this.user = data ?? blankUser;

      console.log("user : ");
      console.log(this.user);

      if (this.user?.id === this.authService.user$.getValue()?.id) {
        this.authenticated = true;
      }
    });

    this.userService.form$.subscribe(data => this.form = data);
  }
}
