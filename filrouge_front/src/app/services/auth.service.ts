import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthRequest } from '../models/AuthRequest.model';
import { ApiRequestService } from './api-request.service';
import { Router } from '@angular/router';
import { AuthResponse } from '../models/AuthResponse.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user$ = new BehaviorSubject<AuthRequest | null>(null);

  constructor(
    private router: Router
  ) { }

  getToken() {
    return localStorage.getItem("jwtToken");
  }

  logIn(user: AuthResponse) {
    localStorage.setItem("jwtToken", user.token);
  }

  signIn() {

  }

  logOut() {
    localStorage.removeItem("jwtToken");
    this.user$.next(null);
    this.router.navigate(['']);
  }
}
