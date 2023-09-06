import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginRequest} from "../payload/requests/login.request";
import {RegisterRequest} from "../payload/requests/register.request";
import {StorageService} from "./storage.service";

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  URL = 'http://localhost:8080/api/auth/';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient, private storage: StorageService) {
  }

  login(data: LoginRequest): Observable<any> {
    return this.http.post(
      this.URL + 'login',
      data,
      this.httpOptions
    );
  }

  register(data: RegisterRequest): Observable<any> {
    return this.http.post(
      this.URL + 'register',
      data,
      this.httpOptions
    );
  }

  logout(): void {
    this.storage.clean();
    window.location.replace("/");
  }

  isLoggedIn(): boolean {
    const jwt = this.storage.getJWToken();
    return !!jwt;
  }
}
