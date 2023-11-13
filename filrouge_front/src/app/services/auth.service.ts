import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { AuthRequest } from '../models/AuthRequest.model';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from '../models/AuthResponse.model';

const baseUrl = "http://localhost:8080/api/auth/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user$ = new BehaviorSubject<string | null>(null);

  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }

  getToken() {
    return localStorage.getItem("jwtToken");
  }

  logIn(authRequest: AuthRequest) {
    this.http.post<AuthResponse>(baseUrl + "authenticate", {
      mail: authRequest.mail,
      password: authRequest.password
    }).subscribe(response => this.authenticate(response));
  }

  signUp(authRequest: AuthRequest) {
    return this.http.post<AuthResponse>(baseUrl + "register", {
      mail: authRequest.mail,
      password: authRequest.password,
      pseudo: authRequest.username,
      birthDate: authRequest.birthDate
    });
  }

  logOut() {
    localStorage.removeItem("jwtToken");
    this.user$.next(null);
    this.router.navigate(['']);
  }

  authenticate(response: AuthResponse) {
    localStorage.setItem("jwtToken", response.token);
    this.user$.next(response.userMail);
  }
}