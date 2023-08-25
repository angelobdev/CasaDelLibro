import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginRequest} from "../payload/requests/login.request";
import {RegisterRequest} from "../payload/requests/register.request";
import {StorageService} from "./storage.service";

const AUTH_API = 'http://localhost:8080/api/auth/'; // TODO: dotenv

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private storage: StorageService) {
  }

  login(data: LoginRequest): Observable<any> {
    return this.http.post(
      AUTH_API + 'login',
      data,
      httpOptions
    );
  }

  register(data: RegisterRequest): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      data,
      httpOptions
    );
  }

  logout(): void {
    this.storage.clean();
  }
}
