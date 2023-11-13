import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequest } from '../models/AuthRequest.model';
import { AuthResponse } from '../models/AuthResponse.model';

const baseUrl = "http://localhost:8080/api/auth/"

@Injectable({
  providedIn: 'root'
})
export class ApiRequestService {

  constructor(private http: HttpClient) { }

  logIn(user: AuthRequest) {
    return this.http.post<AuthResponse>(baseUrl + "authenticate", {
      mail: user.mail,
      password: user.password
    })
  }
}
