import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/auth/models/User.model';
import { AuthService } from 'src/app/auth/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/api/user/";

  user$ = new BehaviorSubject<User | null>(null);

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getUserById(userId: string) {
    this.http.get<User>(this.baseUrl + userId, {
      headers: this.authService.generateHeaders()
    }).subscribe(data => this.user$.next(data));
  }
}
