import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { AuthRequest } from '../models/AuthRequest.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthResponse } from '../models/AuthResponse.model';
import { User } from '../models/User.model';

const baseUrl = "http://localhost:8080/api/auth/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user$ = new BehaviorSubject<User | null>(null);

  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }

  getToken() {
    return localStorage.getItem("jwtToken");
  }

  generateHeaders() {
    return new HttpHeaders().set("Authorization", `Bearer ${this.getToken()}`)
  }

  logIn(authRequest: AuthRequest) {
    return this.http.post<AuthResponse>(baseUrl + "authenticate", {
      mail: authRequest.mail,
      password: authRequest.password
    })
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
    this.user$.next(response.user);
    this.router.navigate(['']);
  }
}
