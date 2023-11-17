// user-detail-form.component.ts
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-detail-form',
  templateUrl: './user-detail-form.component.html',
  styleUrls: ['./user-detail-form.component.css']
})
export class UserDetailFormComponent implements OnInit {

  newUsername: string = '';
  newEmail: string = '';
  newBirthDate: string = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {

  }

  onSubmit(): void {
    const updatedData = {
      username: this.newUsername,
      email: this.newEmail,
      birthDate: this.newBirthDate
    };


    this.userService.editUserData(updatedData);
  }
}
